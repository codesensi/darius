package cn.codesensi.darius.common.enums;

import lombok.Getter;

/**
 * 自定义响应状态枚举
 *
 * @author codesensi
 * @since 2024/1/10 22:44
 */
@Getter
public enum ApiResponseStatus {

    FAIL(0, "失败"),
    OK(1, "成功"),
    BAD_REQUEST(40001, "错误的请求"),
    UNAUTHORIZED(40002, "请求未授权"),
    FORBIDDEN(40003, "请求被拒绝"),
    NOT_FOUND(40004, "未找到该资源"),
    INTERNAL_SERVER_ERROR(50000, "服务器内部错误");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 状态描述
     */
    private final String message;

    ApiResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
