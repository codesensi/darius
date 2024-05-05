package cn.codesensi.darius;

import cn.codesensi.darius.common.constant.Constant;
import cn.codesensi.darius.common.properties.DBProperties;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 项目首次启动时初始化
 */
@Slf4j
@Configuration
public class DataSourceInitialize {

    @Resource
    private DBProperties dbProperties;

    @Value("classpath:db/init-data.sql")
    private org.springframework.core.io.Resource dataSql;

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        final DataSourceInitializer initializer = new DataSourceInitializer();
        // 设置数据源
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        // true表示需要执行，false表示不需要初始化
        initializer.setEnabled(needInitData());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScripts(dataSql);
        populator.setSeparator(";");
        return populator;
    }

    /**
     * 检测一下数据库中表是否存在，若存在则不初始化；否则基于 init-data.sql 进行初始化
     */
    private Boolean needInitData() {
        // 连接地址中的库名
        String databaseName;
        // 配置的连接地址
        String propertiesUrl = dbProperties.getUrl();
        // 没有指定数据库的连接地址
        String noDatabaseUrl;
        try {
            URI databaseUri = new URI(propertiesUrl.replace("jdbc:", ""));
            // 得到连接地址中的数据库平台名（例如mysql）
            String databasePlatform = databaseUri.getScheme();
            // 得到连接地址和端口
            String hostAndPort = databaseUri.getAuthority();
            // 组装不连接至指定库的连接URL
            noDatabaseUrl = "jdbc:" + databasePlatform + "://" + hostAndPort + "/";
            // 得到连接地址中的库名
            databaseName = databaseUri.getPath().substring(Constant.ONE_INT);
        } catch (URISyntaxException e) {
            log.error("获取数据库配置失败：{}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
        // 创建数据库
        this.createDatabase(databaseName, noDatabaseUrl);
        // 查询表名判断是否需要初始化数据
        try (Connection connection = DriverManager.getConnection(propertiesUrl, dbProperties.getUsername(), dbProperties.getPassword());
             Statement statement = connection.createStatement()) {
            ResultSet tableResult = statement.executeQuery("SELECT table_name FROM information_schema.TABLES WHERE table_name LIKE 'sys_%' AND table_schema = '" + databaseName + "'");
            if (tableResult.next()) {
                log.info("初始数据已存在，无需初始化");
                return Boolean.FALSE;
            } else {
                log.info("初始数据不存在，开始初始化数据......");
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            log.error("是否初始化数据判断失败：{}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建数据库
     */
    private void createDatabase(String databaseName, String noDatabaseUrl) {
        try (Connection connection = DriverManager.getConnection(noDatabaseUrl, dbProperties.getUsername(), dbProperties.getPassword());
             Statement statement = connection.createStatement()) {
            // 查询数据库
            ResultSet databaseResult = statement.executeQuery("SELECT schema_name FROM information_schema.schemata WHERE schema_name = '" + databaseName + "'");
            if (databaseResult.next()) {
                log.info("数据库已存在，无需创建");
            } else {
                // 创建数据库
                log.warn("数据库不存在，开始创建数据库......");
                statement.execute("CREATE DATABASE IF NOT EXISTS `" + databaseName + "` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci");
                log.info("创建数据库完成");
            }
        } catch (Exception e) {
            log.error("创建数据库失败：{}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
