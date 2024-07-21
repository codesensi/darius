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

    /**
     * 验证码
     */
    private Captcha captcha;

    /**
     * 验证码
     */
    @Data
    public static class Captcha {

        /**
         * 验证码开关
         */
        private Boolean enabled;

        /**
         * 验证码类型
         */
        private Type type;

        /**
         * 图形验证码类型
         */
        private ImageType imageType;
    }

    /**
     * 验证码类型
     */
    public enum Type {
        /**
         * 短信验证码
         */
        SMS,
        /**
         * 图形验证码
         */
        IMAGE;


        Type() {
        }
    }

    /**
     * 图形验证码类型
     */
    public enum ImageType {
        /**
         * png
         */
        SPEC,
        /**
         * gif
         */
        GIF,
        /**
         * 中文
         */
        CHINESE,
        /**
         * 中文gif
         */
        CHINESE_GIF,
        /**
         * 算术
         */
        ARITHMETIC;

        ImageType() {
        }
    }
}
