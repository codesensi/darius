package cn.codesensi.darius.common.advice;

import cn.codesensi.darius.common.enums.ApiResponseStatus;
import cn.codesensi.darius.common.exception.BusinessException;
import cn.codesensi.darius.common.exception.DemoModeException;
import cn.codesensi.darius.common.exception.SystemException;
import cn.codesensi.darius.common.response.ApiResponseResult;
import cn.codesensi.darius.common.response.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
     * 演示模式异常
     */
    @ExceptionHandler(DemoModeException.class)
    public ApiResponseResult<?> demoModeExceptionHandler(DemoModeException e) {
        log.error("演示模式异常！原因是：{}", e.getMessage(), e);
        return R.fail(ApiResponseStatus.FAIL.getCode(), e.getMessage());
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
