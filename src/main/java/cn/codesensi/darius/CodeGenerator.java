package cn.codesensi.darius;

import cn.codesensi.darius.common.base.BaseEntity;
import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

/**
 * mybatis-plus代码生成器
 *
 * @author codesensi
 * @since 2024/1/10 22:44
 */
public class CodeGenerator {

    /**
     * 常量定义，带*的根据需求修改，其他的不建议修改
     */
    private static final String URL = "jdbc:mysql://192.168.2.101:3306/darius_dev"; // *数据库连接地址
    private static final String USERNAME = "darius_dev"; // *数据库用户名
    private static final String PASSWORD = "darius_dev_dnT9Eb"; // *数据库密码
    private static final String AUTHOR = "codesensi"; // *作者
    private static final String PARENT = "cn.codesensi.darius"; // *父包名
    private static final String MODULE_NAME = "system"; // *父包模块名
    private static final String TABLE_NAMES = "sys_login_log"; // *需要生成的表名,多张表用英文逗号分隔
    private static final String[] TABLE_PREFIX = {"t_", "c_"}; // *过滤表前缀
    // *********************************************************以下部分不建议修改********************************************************************************
    private static final String OUTPUT_DIR = "src\\main\\java"; // 输出目录
    private static final String PATH_INFO = "src\\main\\resources\\mapper\\" + MODULE_NAME; // mapperXml生成路径
    private static final String CONTROLLER = "/templates/generator/controller.java"; // controller模板路径
    private static final String SERVICE = "/templates/generator/service.java"; // service模板路径
    private static final String SERVICE_IMPL = "/templates/generator/serviceImpl.java"; // serviceImpl模板路径
    private static final String MAPPER = "/templates/generator/mapper.java"; // mapper模板路径
    private static final String MAPPER_XML = "/templates/generator/mapper.xml"; // mapperXml模板路径
    private static final String ENTITY = "/templates/generator/entity.java"; // entity模板路径
    private static final String[] IGNORE_COLUMNS = {"creator", "create_time", "updater", "update_time", "delete_flag", "version"}; // 需要过滤的字段


    public static void main(String[] args) {

        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author(AUTHOR) // 设置作者
                            .enableSpringdoc() // 开启springdoc模式
                            .dateType(DateType.TIME_PACK) // 时间策略
                            .commentDate(DatePattern.NORM_DATETIME_PATTERN) // 注释日期
                            .disableOpenDir() // 禁止打开输出目录
                            .outputDir(OUTPUT_DIR); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    // TODO 数据库为tinyint，但此处获取到的是bit，原因未知，后续再详究
                    if (typeCode == Types.SMALLINT || typeCode == Types.TINYINT || typeCode == Types.BIT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);
                }))
                .packageConfig(builder -> {
                    builder.parent(PARENT) // 设置父包名
                            .moduleName(MODULE_NAME) // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, PATH_INFO)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude(TABLE_NAMES) // 设置需要生成的表名
                            .addTablePrefix(TABLE_PREFIX) // 设置过滤表前缀
                            .controllerBuilder() // Controller策略配置
                            .enableHyphenStyle() // 开启驼峰转连字符
                            .enableRestStyle() // 开启生成@RestController 控制器
                            .entityBuilder() // Entity策略配置
                            .superClass(BaseEntity.class) // 设置父类
                            .enableLombok() // 开启 lombok 模型
                            .enableChainModel() // 开启链式模型
                            .addIgnoreColumns(IGNORE_COLUMNS); // 设置需要过滤的字段
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateConfig(builder -> {
                    builder.controller(CONTROLLER) // 设置controller模板路径
                            .service(SERVICE) // 设置service模板路径
                            .serviceImpl(SERVICE_IMPL) // 设置serviceImpl模板路径
                            .mapper(MAPPER) // 设置mapper模板路径
                            .xml(MAPPER_XML) // 设置mapperXml模板路径
                            .entity(ENTITY); // 设置entity模板路径
                })
                .execute();
    }
}
