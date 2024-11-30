package cn.codesensi.darius.common.config.satoken;

import cn.codesensi.darius.common.constant.Constant;
import cn.codesensi.darius.common.properties.DariusProperties;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaHttpMethod;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

/**
 * Sa-Token 拦截器
 *
 * @author codesensi
 * @since 2024/1/21 15:00
 */
@Slf4j
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    /**
     * 注册 Sa-Token 路由拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor(handler -> {
            // 登录校验
            SaRouter.match(Constant.ROOT_PATH).notMatch(Constant.SWAGGER_PATH).check(r -> StpUtil.checkLogin());
            // 系统功能：超级管理员角色
            SaRouter.match(Constant.SYSTEM_PATH).check(r -> StpUtil.checkRole(Constant.ROLE_ADMIN_CODE));
        })).addPathPatterns(Constant.ROOT_PATH);
    }

}
