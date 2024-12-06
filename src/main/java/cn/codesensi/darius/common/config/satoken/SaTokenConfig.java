package cn.codesensi.darius.common.config.satoken;

import cn.codesensi.darius.common.constant.Constant;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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

    /**
     * Sa-Token 整合 jwt (Simple 简单模式)
     */
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }

}
