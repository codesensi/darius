package cn.codesensi.darius.common.annotation;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * 是否构建接口统一响应对象注解
 *
 * @author codesensi
 * @since 2024/1/10 22:44
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody
public @interface ApiResponseBody {
}
