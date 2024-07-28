package cn.codesensi.darius.system.enums;

import lombok.Getter;

/**
 * 授权类型
 *
 * @author codesensi
 * @since 2024/1/13 10:57
 */
@Getter
public enum AuthType {

    /**
     * 未知
     */
    OTHER(0, "未知"),

    /**
     * 登录
     */
    LOGIN(1, "登录"),

    /**
     * 登出
     */
    LOGOUT(2, "登出"),

    /**
     * 封禁
     */
    DISABLE(3, "封禁");

    /**
     * 操作类型编码
     */
    private final Integer code;

    /**
     * 操作类型说明
     */
    private final String message;

    AuthType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
