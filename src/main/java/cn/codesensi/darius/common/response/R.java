package cn.codesensi.darius.common.response;

import cn.codesensi.darius.common.enums.ApiResponseStatusEnum;

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

    private static <T> ApiResponseResult<T> make(ApiResponseStatusEnum status) {
        return new ApiResponseResult<>(status.getCode(), status.getMessage(), null);
    }

    private static <T> ApiResponseResult<T> make(ApiResponseStatusEnum status, T data) {
        return new ApiResponseResult<>(status.getCode(), status.getMessage(), data);
    }

    public static <T> ApiResponseResult<T> ok() {
        return make(ApiResponseStatusEnum.OK);
    }

    public static <T> ApiResponseResult<T> ok(T data) {
        return make(ApiResponseStatusEnum.OK, data);
    }

    public static <T> ApiResponseResult<T> ok(Integer code, String message) {
        return make(code, message, null);
    }

    public static <T> ApiResponseResult<T> ok(Integer code, String message, T data) {
        return make(code, message, data);
    }

    public static <T> ApiResponseResult<T> fail() {
        return make(ApiResponseStatusEnum.FAIL);
    }

    public static <T> ApiResponseResult<T> fail(T data) {
        return make(ApiResponseStatusEnum.FAIL, data);
    }

    public static <T> ApiResponseResult<T> fail(Integer code, String message) {
        return make(code, message, null);
    }

    public static <T> ApiResponseResult<T> fail(Integer code, String message, T data) {
        return make(code, message, data);
    }
}
