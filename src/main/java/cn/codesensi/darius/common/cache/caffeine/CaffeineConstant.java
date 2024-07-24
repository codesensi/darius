package cn.codesensi.darius.common.cache.caffeine;

public class CaffeineConstant {

    // -------------------- 缓存名称 --------------------
    /**
     * 默认缓存名称
     */
    public static final String CACHE_DEFAULT = "CACHE:DEFAULT";
    /**
     * 图形验证码
     */
    public static final String CAPTCHA_IMAGE = "CAPTCHA:IMAGE";
    /**
     * 短信验证码
     */
    public static final String CAPTCHA_SMS = "CAPTCHA:SMS";
    /**
     * 用户角色权限码
     */
    public static final String USER_ROLE = "USER:ROLE";
    /**
     * 用户菜单权限码
     */
    public static final String USER_MENU = "USER:MENU";


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
