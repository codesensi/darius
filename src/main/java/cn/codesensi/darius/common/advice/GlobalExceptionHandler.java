package cn.codesensi.darius.common.advice;

import cn.codesensi.darius.common.enums.ApiResponseStatus;
import cn.codesensi.darius.common.exception.AuthException;
import cn.codesensi.darius.common.exception.BusinessException;
import cn.codesensi.darius.common.exception.ModeException;
import cn.codesensi.darius.common.exception.SystemException;
import cn.codesensi.darius.common.response.ApiResponseResult;
import cn.codesensi.darius.common.response.R;
import cn.dev33.satoken.exception.NotLoginException;
import cn.hutool.core.util.ObjUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

/**
 * 全局异常处理
 *
 * @author codesensi
 * @since 2024/1/10 22:44
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 系统异常
     */
    @ExceptionHandler(SystemException.class)
    public ApiResponseResult<?> systemExceptionHandler(SystemException e) {
        log.error("系统异常！原因是：{}", e.getMessage(), e);
        return R.fail(ApiResponseStatus.FAIL.getCode(), e.getMessage());
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ApiResponseResult<?> businessExceptionHandler(BusinessException e) {
        log.error("业务异常！原因是：{}", e.getMessage(), e);
        return R.fail(ApiResponseStatus.FAIL.getCode(), e.getMessage());
    }

    /**
     * 模式异常
     */
    @ExceptionHandler(ModeException.class)
    public ApiResponseResult<?> modeExceptionHandler(ModeException e) {
        log.error("模式异常！原因是：{}", e.getMessage(), e);
        return R.fail(ApiResponseStatus.FAIL.getCode(), e.getMessage());
    }

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public ApiResponseResult<?> nullPointerExceptionHandler(NullPointerException e) {
        log.error("空指针异常！原因是：{}", e.getMessage(), e);
        return R.fail(ApiResponseStatus.INTERNAL_ERROR);
    }

    /**
     * 未找到资源异常
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ApiResponseResult<?> noResourceFoundExceptionHandler(NoResourceFoundException e) {
        log.error("未找到资源异常！原因是：{}", e.getMessage(), e);
        return R.fail(ApiResponseStatus.NOT_FOUND);
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponseResult<?> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("参数校验异常！原因是：{}", e.getMessage(), e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        String message = "参数校验不通过";
        if (ObjUtil.isNotNull(fieldError)) {
            message = fieldError.getDefaultMessage();
        }
        return R.fail(ApiResponseStatus.FAIL.getCode(), message);
    }

    /**
     * 账号鉴权异常
     */
    @ExceptionHandler(AuthException.class)
    public ApiResponseResult<?> authExceptionHandler(AuthException e) {
        log.error("账号鉴权异常！原因是：{}", e.getMessage(), e);
        return R.fail(ApiResponseStatus.FAIL.getCode(), e.getMessage());
    }

    /**
     * 账号状态异常
     */
    @ExceptionHandler(NotLoginException.class)
    public ApiResponseResult<?> notLoginExceptionHandler(NotLoginException e) {
        log.error("账号状态异常！原因是：{}", e.getMessage(), e);
        if (NotLoginException.TOKEN_FREEZE.equals(e.getType())) {
            return R.fail(ApiResponseStatus.ACCOUNT_FREEZE);
        }
        return R.fail(ApiResponseStatus.NOT_LOGIN);
    }

    /**
     * 未知异常
     */
    @ExceptionHandler(Exception.class)
    public ApiResponseResult<?> exceptionHandler(Exception e) {
        log.error("未知异常！原因是：{}", e.getMessage(), e);
        return R.fail(ApiResponseStatus.FAIL.getCode(), e.getMessage());
    }

}
