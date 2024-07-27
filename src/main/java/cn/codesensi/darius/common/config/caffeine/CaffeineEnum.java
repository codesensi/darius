package cn.codesensi.darius.common.config.caffeine;

import lombok.Getter;

/**
 * Caffeine缓存枚举
 */
@Getter
public enum CaffeineEnum {

    /**
     * 验证码缓存
     */
    CAPTCHA_IMAGE(CaffeineConstant.CACHE_CAPTCHA, CaffeineConstant.EXPIRE_5_MIN),
    /**
     * 用户缓存
     */
    USER_ROLE(CaffeineConstant.CACHE_USER, CaffeineConstant.EXPIRE_DEFAULT),;

    /**
     * 缓存名称
     */
    private final String name;
    /**
     * 缓存过期秒数
     */
    private final Long expire;

    CaffeineEnum(String name, Long expire) {
        this.name = name;
        this.expire = expire;
    }
}
