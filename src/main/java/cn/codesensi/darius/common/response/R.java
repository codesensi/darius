package cn.codesensi.darius.common.response;

/**
 * 接口统一响应对象构建工具
 *
 * @author codesensi
 * @since 2024/1/10 22:44
 */
public class R {
    private static <T> Result<T> make(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }

    private static <T> Result<T> make(ResultStatus status) {
        return new Result<>(status.getCode(), status.getMessage(), null);
    }

    private static <T> Result<T> make(ResultStatus status, T data) {
        return new Result<>(status.getCode(), status.getMessage(), data);
    }

    public static <T> Result<T> ok() {
        return make(ResultStatus.OK);
    }

    public static <T> Result<T> ok(ResultStatus status) {
        return make(status);
    }

    public static <T> Result<T> ok(T data) {
        return make(ResultStatus.OK, data);
    }

    public static <T> Result<T> ok(Integer code, String message) {
        return make(code, message, null);
    }

    public static <T> Result<T> ok(Integer code, String message, T data) {
        return make(code, message, data);
    }

    public static <T> Result<T> fail() {
        return make(ResultStatus.FAIL);
    }

    public static <T> Result<T> fail(ResultStatus status) {
        return make(status);
    }

    public static <T> Result<T> fail(T data) {
        return make(ResultStatus.FAIL, data);
    }

    public static <T> Result<T> fail(Integer code, String message) {
        return make(code, message, null);
    }

    public static <T> Result<T> fail(Integer code, String message, T data) {
        return make(code, message, data);
    }
}
