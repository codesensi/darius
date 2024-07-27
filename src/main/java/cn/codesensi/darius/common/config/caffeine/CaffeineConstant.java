package cn.codesensi.darius.common.config.caffeine;

public class CaffeineConstant {

    // -------------------- 缓存名称 --------------------
    /**
     * 默认缓存
     */
    public static final String CACHE_DEFAULT = "default";
    /**
     * 验证码缓存
     */
    public static final String CACHE_CAPTCHA = "captcha";
    /**
     * 用户缓存
     */
    public static final String CACHE_USER = "user";


    // -------------------- 最大缓存数量 --------------------
    /**
     * 默认：1000
     */
    public static final Long SIZE_DEFAULT = 1000L;


    // -------------------- 缓存过期秒数 --------------------
    /**
     * 默认：30天
     */
    public static final Long EXPIRE_DEFAULT = 43200L;
    /**
     * 5分钟过期
     */
    public static final Long EXPIRE_5_MIN = 300L;

}
