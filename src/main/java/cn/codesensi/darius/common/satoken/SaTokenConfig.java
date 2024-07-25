package cn.codesensi.darius.common.satoken;

import cn.codesensi.darius.common.properties.DariusProperties;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * Sa-Token 拦截器
 *
 * @author codesensi
 * @since 2024/1/21 15:00
 */
@Slf4j
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    @Resource
    private DariusProperties dariusProperties;

    /**
     * 注册 Sa-Token 路由拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 从配置文件中加载鉴权路径
        DariusProperties.SaToken saToken = dariusProperties.getSaToken();
        List<String> allPath = Arrays.asList(saToken.getAllPath().split(","));
        registry.addInterceptor(new SaInterceptor(handler -> {
            // 登录校验
            SaRouter.match(allPath).notMatch(Arrays.asList(saToken.getLoginNotMatchPath().split(","))).check(r -> StpUtil.checkLogin());
        })).addPathPatterns(allPath);
    }

}
