package cn.codesensi.darius.common.annotation;


import cn.codesensi.darius.common.enums.OperateTypeEnum;

import java.lang.annotation.*;

/**
 * 是否记录日志注解
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
    OperateTypeEnum operateType() default OperateTypeEnum.UNKNOWN;

    /**
     * 日志描述
     */
    String description() default "";
}
