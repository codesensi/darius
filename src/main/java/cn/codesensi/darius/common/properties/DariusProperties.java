package cn.codesensi.darius.common.properties;

import com.github.benmanes.caffeine.cache.CaffeineSpec;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 项目配置实体类
 */
@Data
@Component
@ConfigurationProperties(prefix = "darius")
public class DariusProperties {

    /**
     * 演示模式
     */
    private Boolean demoMode;

    /**
     * 验证码
     */
    private Captcha captcha;

    /**
     * 自定义sa-token配置
     */
    private SaToken saToken;

    /**
     * 本地缓存配置
     */
    private Caffeine caffeine;

    /**
     * 本地缓存配置
     */
    @Data
    public static class Caffeine {

        /**
         * 默认规则
         */
        private String cacheSpecification;

        /**
         * 自定义规则
         */
        private Map<String, String> caffeineSpec;
    }

    /**
     * 自定义sa-token配置
     */
    @Data
    public static class SaToken {

        /**
         * 全路径
         */
        private String allPath;

        /**
         * 不校验登录路径
         */
        private String loginNotMatchPath;
    }

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
