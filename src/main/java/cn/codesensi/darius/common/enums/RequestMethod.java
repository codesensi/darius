package cn.codesensi.darius.common.enums;

import lombok.Getter;

/**
 * 请求方式
 *
 * @author codesensi
 * @since 2024/1/13 10:57
 */
@Getter
public enum RequestMethod {

    /**
     * GET请求
     */
    GET("GET", "GET"),

    /**
     * PUT请求
     */
    PUT("PUT", "PUT"),

    /**
     * POST请求
     */
    POST("POST", "POST"),

    /**
     * DELETE请求
     */
    DELETE("DELETE", "DELETE");

    /**
     * 请求方式编码
     */
    private final String code;

    /**
     * 请求方式说明
     */
    private final String message;

    RequestMethod(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
