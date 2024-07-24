package cn.codesensi.darius.common.cache.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineConfig {

    /**
     * 缓存管理器配置
     * 使用注解自定义缓存名称，须在CaffeineEnum中定义缓存枚举，否则会报错找不到缓存名称
     */
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<CaffeineCache> list = new ArrayList<>();
        // 循环添加枚举类中自定义的缓存，可以自定义
        for (CaffeineEnum cacheEnum : CaffeineEnum.values()) {
            list.add(new CaffeineCache(cacheEnum.getCacheName(),
                    Caffeine.newBuilder()
                            .maximumSize(cacheEnum.getMaximumSize())
                            .expireAfterAccess(cacheEnum.getExpireAfterAccess(), TimeUnit.SECONDS)
                            .recordStats()
                            .build(),
                    false));
        }
        cacheManager.setCaches(list);
        return cacheManager;
    }

}
