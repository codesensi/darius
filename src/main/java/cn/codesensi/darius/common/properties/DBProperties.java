package cn.codesensi.darius.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 数据库配置实体类
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DBProperties {

    /**
     * 数据源类型
     */
    private String type;
    /**
     * 数据库驱动程序类名
     */
    private String driverClassName;
    /**
     * 数据库连接地址
     */
    private String url;
    /**
     * 数据库连接用户名
     */
    private String username;
    /**
     * 数据库连接用户密码
     */
    private String password;
}
