package cn.codesensi.darius.system.advice;

import cn.codesensi.darius.common.annotation.ApiResponseBody;
import cn.codesensi.darius.common.response.Result;
import cn.codesensi.darius.common.response.R;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;

/**
 * 封装接口统一响应对象
 * 实现ResponseBodyAdvice的方法后，接口上就可以不用返回统一响应对象
 *
 * @author codesensi
 * @since 2024/1/10 22:44
 */
@Slf4j
@RestControllerAdvice
public class ApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final Class<? extends Annotation> ANNOTATION_TYPE = ApiResponseBody.class;

    /**
     * 判断类或者方法是否使用了@ResponseResultBody注解
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> clazz) {
        return AnnotatedElementUtils.hasAnnotation(methodParameter.getContainingClass(), ANNOTATION_TYPE) || methodParameter.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    /**
     * 当类或者方法使用了@ResponseResultBody注解就会调用这个方法将响应结果封装为接口统一响应对象
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> clazz, ServerHttpRequest request, ServerHttpResponse response) {
        // 如果返回类型是string，那么springmvc是直接返回的，此时需要手动转化为json
        if (body instanceof String) {
            return JSONUtil.toJsonStr(R.ok(body));
        }
        // 防止重复包裹的问题出现
        if (body instanceof Result) {
            return body;
        }
        return R.ok(body);
    }

}
