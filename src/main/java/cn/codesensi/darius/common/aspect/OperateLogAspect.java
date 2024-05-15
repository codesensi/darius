package cn.codesensi.darius.common.aspect;

import cn.codesensi.darius.common.annotation.OperateLog;
import cn.codesensi.darius.common.constant.Constant;
import cn.codesensi.darius.common.task.TaskFactory;
import cn.codesensi.darius.common.task.TaskManager;
import cn.codesensi.darius.common.util.Ip2regionUtil;
import cn.codesensi.darius.common.util.IpUtil;
import cn.codesensi.darius.common.util.ServletUtil;
import cn.codesensi.darius.system.entity.SysOperateLog;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;
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

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new NamedThreadLocal<>("Cost Time");

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
    public void doBefore() {
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    /**
     * 返回通知
     */
    @AfterReturning(pointcut = "doPointcut()", returning = "returning")
    public void doAfterReturning(JoinPoint joinPoint, Object returning) {
        handleLog(joinPoint, null, returning);
    }

    /**
     * 异常通知
     */
    @AfterThrowing(value = "doPointcut()", throwing = "throwable")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        handleLog(joinPoint, throwable, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Throwable throwable, Object returning) {
        try {
            ServletRequestAttributes attributes = ServletUtil.getRequestAttributes();
            Optional.ofNullable(attributes).ifPresent(o -> {
                HttpServletRequest request = o.getRequest();
                Signature signature = joinPoint.getSignature();
                StringBuffer requestUrl = request.getRequestURL();
                String requestMethod = StrUtil.join(".", signature.getDeclaringTypeName(), signature.getName());
                String requestMode = request.getMethod();

                SysOperateLog operateLog = new SysOperateLog();
                // 获取注解信息
                OperateLog annotationLog = getAnnotationLog(joinPoint);
                Optional.ofNullable(annotationLog).ifPresent(l -> {
                    operateLog.setOperateType(l.operateType().getCode());
                    operateLog.setOperateMessage(l.operateType().getMessage());
                    operateLog.setOperateDescription(l.description());
                    // 请求参数
                    if (l.isSaveRequestParam()) {
                        String[] names = ((CodeSignature) signature).getParameterNames();
                        Object[] values = joinPoint.getArgs();
                        Map<String, Object> paramsMap = new HashMap<>();
                        for (int i = 0; i < names.length; i++) {
                            paramsMap.put(names[i], values[i]);
                        }
                        String requestParam = JSONUtil.toJsonStr(paramsMap);
                        operateLog.setRequestParam(requestParam);
                    }
                    // 响应数据
                    if (l.isSaveResponseData()) {
                        String responseData = JSONUtil.toJsonStr(returning);
                        operateLog.setResponseData(responseData);
                    }
                });
                // 请求
                // TODO 请求人
                operateLog.setRequestUser(null);
                operateLog.setRequestTime(LocalDateTime.now());
                String ipAddr = IpUtil.getIpAddr();
                operateLog.setRequestIp(ipAddr);
                operateLog.setRequestArea(Ip2regionUtil.search(ipAddr));
                UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtil.getUserAgent());
                operateLog.setRequestOs(userAgent.getOperatingSystem().getName());
                operateLog.setRequestDevice(userAgent.getOperatingSystem().getDeviceType().getName());
                operateLog.setRequestBrowser(userAgent.getBrowser().getName());
                operateLog.setRequestUrl(requestUrl.toString());
                operateLog.setRequestMethod(requestMethod);
                operateLog.setRequestMode(requestMode);
                // 响应
                operateLog.setResponseState(Constant.ONE_INT);
                operateLog.setResponseTime(LocalDateTime.now());
                operateLog.setResponseConsume(System.currentTimeMillis() - TIME_THREADLOCAL.get());
                // 异常
                if (throwable != null) {
                    operateLog.setResponseState(Constant.ZERO_INT);
                    operateLog.setErrorTime(LocalDateTime.now());
                    operateLog.setErrorMessage(throwable.getMessage());
                }
                // TODO 创建人
                operateLog.setCreator(null);
                // 异步保存数据库
                TaskManager.me().execute(TaskFactory.recordOperateLog(operateLog));
            });
        } catch (Exception e) {
            // 记录本地异常日志
            log.error("操作日志记录异常！原因是：{}", e.getMessage(), e);
        } finally {
            TIME_THREADLOCAL.remove();
        }
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
