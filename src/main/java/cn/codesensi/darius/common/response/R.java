package cn.codesensi.darius.common.response;

import cn.codesensi.darius.common.enums.ApiResponseStatus;

/**
 * 接口统一响应对象构建工具
 *
 * @author codesensi
 * @since 2024/1/10 22:44
 */
public class R {
    private static <T> ApiResponseResult<T> make(Integer code, String message, T data) {
        return new ApiResponseResult<>(code, message, data);
    }

    private static <T> ApiResponseResult<T> make(ApiResponseStatus status) {
        return new ApiResponseResult<>(status.getCode(), status.getMessage(), null);
    }

    private static <T> ApiResponseResult<T> make(ApiResponseStatus status, T data) {
        return new ApiResponseResult<>(status.getCode(), status.getMessage(), data);
    }

    public static <T> ApiResponseResult<T> ok() {
        return make(ApiResponseStatus.OK);
    }

    public static <T> ApiResponseResult<T> ok(ApiResponseStatus status) {
        return make(status);
    }

    public static <T> ApiResponseResult<T> ok(T data) {
        return make(ApiResponseStatus.OK, data);
    }

    public static <T> ApiResponseResult<T> ok(Integer code, String message) {
        return make(code, message, null);
    }

    public static <T> ApiResponseResult<T> ok(Integer code, String message, T data) {
        return make(code, message, data);
    }

    public static <T> ApiResponseResult<T> fail() {
        return make(ApiResponseStatus.FAIL);
    }

    public static <T> ApiResponseResult<T> fail(ApiResponseStatus status) {
        return make(status);
    }

    public static <T> ApiResponseResult<T> fail(T data) {
        return make(ApiResponseStatus.FAIL, data);
    }

    public static <T> ApiResponseResult<T> fail(Integer code, String message) {
        return make(code, message, null);
    }

    public static <T> ApiResponseResult<T> fail(Integer code, String message, T data) {
        return make(code, message, data);
    }
}
