package cn.codesensi.darius.common.aspect;

import cn.codesensi.darius.common.annotation.OperateLog;
import cn.codesensi.darius.common.util.IpUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 接口请求切面日志
 *
 * @author codesensi
 * @since 2024/1/10 22:44
 */
@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    /**
     * 请求唯一id的key
     */
    private final String KEY_REQUEST_ID = "requestId";

    /**
     * 请求开始时间戳
     */
    private Long start;

    /**
     * 请求结束时间戳
     */
    private Long end;

    public OperateLogAspect() {
    }

    /**
     * 切点
     * Pointcut("execution(* cn.codesensi.nav4j..*.controller..*.*(..))"):当前包下记录日志
     * Pointcut("@annotation(cn.codesensi.nav4j.annotation.OperateLog)"):有OperateLog注解的类或方法记录日志
     */
    // @Pointcut("execution(* cn.codesensi.darius..*.controller..*.*(..))")
    @Pointcut("@annotation(cn.codesensi.darius.common.annotation.OperateLog)")
    public void doPointcut() {
    }

    /**
     * 前置通知
     */
    @Before("doPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String requestId = IdUtil.getSnowflakeNextIdStr();
        Optional.ofNullable(attributes).ifPresent(o -> {
            log.info("====================请求接口开始start====================");
            MDC.put(KEY_REQUEST_ID, requestId);
            log.info("|--[前置通知]存放请求唯一id：{}", requestId);
            start = System.currentTimeMillis();
            HttpServletRequest request = o.getRequest();
            Signature signature = joinPoint.getSignature();
            log.info("|--开始时间：{}", LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.NORM_DATETIME_MS_PATTERN));
            String ipAddr = IpUtil.getIpAddr(request);
            log.info("|--请求IP：{}", ipAddr);
            log.info("|--请求接口：{}", request.getRequestURL());
            // 注解日志
            OperateLog annotationLog = getAnnotationLog(joinPoint);
            Optional.ofNullable(annotationLog).ifPresent(l -> {
                log.info("|--操作类型：{}", l.operateType().getMessage());
                log.info("|--操作描述：{}", l.description());
            });
            log.info("|--请求方法：{}.{}", signature.getDeclaringTypeName(), signature.getName());
            log.info("|--请求方式：{}", request.getMethod());
            // 组织请求参数 key-value
            String[] names = ((CodeSignature) signature).getParameterNames();
            Object[] values = joinPoint.getArgs();
            Map<String, Object> paramsMap = new HashMap<>();
            for (int i = 0; i < names.length; i++) {
                paramsMap.put(names[i], values[i]);
            }
            log.info("|--请求参数：{}", JSONUtil.toJsonStr(paramsMap));
            log.info("====================请求接口开始end====================");
        });
    }

    /**
     * 返回通知
     */
    @AfterReturning(pointcut = "doPointcut()", returning = "returning")
    public void doAfterReturning(Object returning) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Optional.ofNullable(attributes).ifPresent(o -> {
            log.info("====================请求接口结束start====================");
            String requestId = MDC.get(KEY_REQUEST_ID);
            log.info("|--[返回通知]获取请求唯一id：{}", requestId);
            // 方法结束时间戳
            end = System.currentTimeMillis();
            log.info("|--结束时间：{}", LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.NORM_DATETIME_MS_PATTERN));
            log.info("|--请求耗时：{}ms", (end - start));
            // 返回内容
            log.info("|--请求返回：{}", JSONUtil.toJsonStr(returning));
            log.info("====================请求接口结束end====================");
        });
    }

    /**
     * 异常通知
     */
    @AfterThrowing(value = "doPointcut()", throwing = "throwable")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Optional.ofNullable(attributes).ifPresent(o -> {
            log.error("====================请求接口异常start====================");
            String requestId = MDC.get(KEY_REQUEST_ID);
            log.info("|--[异常通知]获取请求唯一id：{}", requestId);
            HttpServletRequest request = o.getRequest();
            String ipAddr = IpUtil.getIpAddr(request);
            log.error("|--请求IP：{}", ipAddr);
            log.error("|--异常接口：{}", request.getRequestURL());
            // 注解日志
            OperateLog annotationLog = getAnnotationLog(joinPoint);
            Optional.ofNullable(annotationLog).ifPresent(l -> {
                log.error("|--操作类型：{}", l.operateType().getMessage());
                log.error("|--操作描述：{}", l.description());
            });
            log.error("|--异常时间：{}", LocalDateTimeUtil.format(LocalDateTime.now(), DatePattern.NORM_DATETIME_MS_PATTERN));
            log.error("|--异常原因：{}", throwable.getMessage());
            log.error("====================请求接口异常end====================");
        });
    }

    /**
     * 获取存在的注解
     */
    private OperateLog getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(OperateLog.class);
        }
        return null;
    }

}
