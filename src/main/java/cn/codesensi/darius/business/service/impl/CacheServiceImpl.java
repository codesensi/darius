package cn.codesensi.darius.business.service.impl;

import cn.codesensi.darius.business.service.CacheService;
import cn.codesensi.darius.common.cache.caffeine.CaffeineConstant;
import cn.hutool.core.util.StrUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 缓存接口实现
 *
 * @author codesensi
 * @since 2024-07-21 11:22:32
 */
@Slf4j
@Service
public class CacheServiceImpl implements CacheService {

    @Resource
    private CacheManager cacheManager;

    /**
     * 获取缓存内容
     *
     * @param cacheName 缓存名称
     * @param key       缓存key
     */
    @Override
    public Object getValue(String cacheName, String key) {
        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache(cacheName);
        if (caffeineCache != null) {
            Cache<Object, Object> cache = caffeineCache.getNativeCache();
            Object value = cache.getIfPresent(key);
            log.info("从缓存中获取到键值：{}:{}", key, value);
            return value;
        }
        return null;
    }

    /**
     * 放入缓存
     *
     * @param cacheName 缓存名称 默认：CACHE:DEFAULT
     * @param key       缓存key
     * @param value     缓存值
     * @param expire    过期秒数 默认：30天
     */
    @Override
    public void put(String cacheName, Object key, Object value, Long expire) {
        if (key == null || value == null) {
            return;
        }
        if (StrUtil.isBlank(cacheName)) {
            cacheName = CaffeineConstant.CACHE_DEFAULT;
        }
        if (expire == null) {
            expire = CaffeineConstant.EXPIRE_DEFAULT;
        }
        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache(cacheName);
        if (caffeineCache == null) {
            caffeineCache = new CaffeineCache(cacheName, Caffeine.newBuilder()
                    .expireAfterWrite(expire, TimeUnit.SECONDS)
                    .recordStats()
                    .build(),
                    false);
        }
        caffeineCache.put(key, value);
        log.info("放入缓存信息，缓存名称：{}，缓存键值：{}:{}，过期时间：{}", cacheName, key, value, expire);
    }
}
