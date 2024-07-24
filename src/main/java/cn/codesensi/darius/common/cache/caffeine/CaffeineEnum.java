package cn.codesensi.darius.common.cache.caffeine;

import lombok.Getter;

/**
 * Caffeine缓存枚举
 */
@Getter
public enum CaffeineEnum {

    /**
     * 默认缓存
     */
    CACHE_DEFAULT(CaffeineConstant.CACHE_DEFAULT, CaffeineConstant.SIZE_DEFAULT, CaffeineConstant.EXPIRE_DEFAULT),
    /**
     * 图形验证码
     */
    CAPTCHA_IMAGE(CaffeineConstant.CAPTCHA_IMAGE, CaffeineConstant.SIZE_DEFAULT, CaffeineConstant.EXPIRE_5_MIN),
    /**
     * 短信验证码
     */
    CAPTCHA_SMS(CaffeineConstant.CAPTCHA_SMS, CaffeineConstant.SIZE_DEFAULT, CaffeineConstant.EXPIRE_5_MIN),
    /**
     * 用户角色权限码
     */
    USER_ROLE(CaffeineConstant.USER_ROLE, CaffeineConstant.SIZE_DEFAULT, CaffeineConstant.EXPIRE_DEFAULT),
    /**
     * 用户菜单权限码
     */
    USER_MENU(CaffeineConstant.USER_MENU, CaffeineConstant.SIZE_DEFAULT, CaffeineConstant.EXPIRE_DEFAULT);

    /**
     * 缓存名称
     */
    private final String cacheName;
    /**
     * 最大缓存数量
     */
    private final Long maximumSize;
    /**
     * 缓存过期秒数
     */
    private final Long expireAfterAccess;

    CaffeineEnum(String cacheName, Long maximumSize, Long expireAfterAccess) {
        this.cacheName = cacheName;
        this.maximumSize = maximumSize;
        this.expireAfterAccess = expireAfterAccess;
    }
}
