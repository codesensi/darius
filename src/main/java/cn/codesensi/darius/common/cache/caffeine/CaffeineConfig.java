package cn.codesensi.darius.common.cache.caffeine;

import cn.codesensi.darius.common.properties.DariusProperties;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaffeineConfig {

    @Resource
    private DariusProperties dariusProperties;

    /**
     * Caffeine缓存配置
     */
    @Bean
    public DariusCaffeineCacheManager dariusCaffeineCacheManager() {
        DariusCaffeineCacheManager dariusCaffeineCacheManager = new DariusCaffeineCacheManager();
        // 异步保存缓存
        dariusCaffeineCacheManager.setAsyncCacheMode(true);
        // 不保存空值
        dariusCaffeineCacheManager.setAllowNullValues(false);
        // 加载默认缓则规则
        dariusCaffeineCacheManager.setCacheSpecification(dariusProperties.getCaffeine().getCacheSpecification());
        // 加载自定义缓存规则
        dariusCaffeineCacheManager.setCaffeineSpec(dariusProperties.getCaffeine().getCaffeineSpec());
        return dariusCaffeineCacheManager;
    }

}
