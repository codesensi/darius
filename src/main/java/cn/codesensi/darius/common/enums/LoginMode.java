package cn.codesensi.darius.common.enums;

import lombok.Getter;

/**
 * 登录方式
 *
 * @author codesensi
 * @since 2024/1/13 10:57
 */
@Getter
public enum LoginMode {

    /**
     * 未知
     */
    OTHER(0, "未知"),

    /**
     * 账号密码
     */
    ACCOUNT(1, "账号密码"),

    /**
     * 手机验证码
     */
    MOBILE(2, "手机验证码");

    /**
     * 操作类型编码
     */
    private final Integer code;

    /**
     * 操作类型说明
     */
    private final String message;

    LoginMode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
