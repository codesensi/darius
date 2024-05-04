package cn.codesensi.darius.common.advice;

import cn.codesensi.darius.common.exception.BusinessException;
import cn.codesensi.darius.common.exception.SystemException;
import cn.codesensi.darius.common.response.ApiResponseResult;
import cn.codesensi.darius.common.enums.ApiResponseStatusEnum;
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
     * 全局异常处理
     */
    @ExceptionHandler(value = Exception.class)
    public ApiResponseResult<?> exceptionHandler(Exception e) {
        if (e instanceof SystemException) {
            log.error("系统异常！原因是：{}", e.getMessage(), e);
        }
        if (e instanceof BusinessException) {
            log.error("业务异常！原因是：{}", e.getMessage(), e);
        }
        return R.fail(ApiResponseStatusEnum.FAIL.getCode(), e.getMessage());
    }
}
