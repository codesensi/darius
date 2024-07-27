package cn.codesensi.darius.common.config.caffeine;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class CaffeineConfig {


    /**
     * Caffeine缓存配置
     */
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> caches = new ArrayList<>();
        // 循环添加枚举类中自定义的缓存
        for (CaffeineEnum cacheEnum : CaffeineEnum.values()) {
            caches.add(CaffeineUtil.buildCaffeineCache(cacheEnum.getName(), CaffeineConstant.SIZE_DEFAULT, cacheEnum.getExpire()));
        }
        cacheManager.setCaches(caches);
        return cacheManager;
    }

}
