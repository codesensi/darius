package cn.codesensi.darius.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 项目配置实体类
 */
@Data
@Component
@ConfigurationProperties(prefix = "darius.config")
public class DariusConfigProperties {

    /**
     * 演示模式
     */
    private Boolean demoMode;
}
