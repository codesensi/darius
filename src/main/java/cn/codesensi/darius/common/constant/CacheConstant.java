package cn.codesensi.darius.common.constant;

/**
 * 缓存常量
 */
public class CacheConstant {
    // -------------------- 缓存名称 --------------------
    /**
     * 验证码缓存
     */
    public static final String CACHE_CAPTCHA = "captcha";
    /**
     * 用户缓存
     */
    public static final String CACHE_USER = "user";

    // -------------------- 缓存过期秒数 --------------------
    /**
     * 30天过期
     */
    public static final Long EXPIRE_DEFAULT = 43200L;
    /**
     * 5分钟过期
     */
    public static final Long EXPIRE_5_MIN = 300L;
}
