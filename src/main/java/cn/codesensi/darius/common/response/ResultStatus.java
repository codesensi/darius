package cn.codesensi.darius.common.response;

import lombok.Getter;

/**
 * 自定义响应状态枚举
 *
 * @author codesensi
 * @since 2024/1/10 22:44
 */
@Getter
public enum ResultStatus {

    FAIL(0, "失败"),
    OK(1, "成功"),

    // ---------- 通用状态码 ----------
    BAD_REQUEST(400, "错误的请求"),
    UNAUTHORIZED(401, "请求未授权"),
    FORBIDDEN(403, "请求被拒绝"),
    NOT_FOUND(404, "未找到该资源"),
    INTERNAL_ERROR(500, "服务器内部错误"),

    // ---------- 账号状态码 ----------
    ACCOUNT_ERROR(2000, "账号异常"),
    NOT_LOGIN(-1, "账号未登录"),
    ACCOUNT_FREEZE(-6, "账号被冻结"),
    NOT_ROLE(2001, "角色权限不足"),
    NOT_PERMIT(2002, "账号权限不足"),

    // ---------- 参数校验状态码 ----------
    PARAMETER_MISSING(1001, "缺少必须参数"),
    PARAMETER_VERIFY(1002, "参数校验不通过");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 状态描述
     */
    private final String message;

    ResultStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
