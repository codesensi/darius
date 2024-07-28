package cn.codesensi.darius.common.annotation;


import cn.codesensi.darius.system.enums.OperateType;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 *
 * @author codesensi
 * @since 2024/1/12 22:05
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Documented
public @interface OperateLog {

    /**
     * 日志类型
     */
    OperateType operateType() default OperateType.OTHER;

    /**
     * 日志描述
     */
    String description() default "";

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestParam() default true;

    /**
     * 是否保存响应的参数
     */
    boolean isSaveResponseData() default true;

}
