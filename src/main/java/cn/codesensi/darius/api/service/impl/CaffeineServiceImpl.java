package cn.codesensi.darius.api.service.impl;

import cn.codesensi.darius.api.service.CaffeineService;
import cn.codesensi.darius.api.vo.CaffeineListVO;
import cn.codesensi.darius.api.vo.CaffeineStatsVO;
import cn.codesensi.darius.common.config.caffeine.CaffeineConstant;
import cn.codesensi.darius.common.config.caffeine.CaffeineUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

/**
 * Caffeine缓存接口实现
 *
 * @author codesensi
 * @since 2024-07-21 11:22:32
 */
@Slf4j
@Service
public class CaffeineServiceImpl implements CaffeineService {

    @Resource
    private CacheManager cacheManager;

    /**
     * 获取缓存值
     *
     * @param name 缓存名称
     * @param key  缓存键
     */
    @Override
    public Object get(String name, Object key) {
        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache(name);
        if (caffeineCache != null) {
            Object value = caffeineCache.get(key, Object.class);
            log.info("获取缓存成功，缓存名称：{}，键值：{}:{}", name, key, value);
            return value;
        }
        return null;
    }

    /**
     * 放入缓存
     *
     * @param name   缓存名称
     * @param key    缓存键
     * @param value  缓存值
     * @param expire 过期秒数
     */
    @Override
    public void put(String name, Object key, Object value, Long expire) {
        if (key == null) {
            return;
        }
        if (StrUtil.isBlank(name)) {
            name = CaffeineConstant.CACHE_DEFAULT;
        }
        if (expire == null) {
            expire = CaffeineConstant.EXPIRE_DEFAULT;
        }
        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache(name);
        if (caffeineCache == null) {
            caffeineCache = CaffeineUtil.buildCaffeineCache(name, CaffeineConstant.SIZE_DEFAULT, expire);
        }
        caffeineCache.put(key, value);
        log.info("缓存成功，缓存名称：{}，键值：{}:{}，过期秒数：{}", name, key, value, expire);
    }

    /**
     * 删除缓存
     *
     * @param name 缓存名称
     * @param key  缓存键
     */
    @Override
    public void evict(String name, Object key) {
        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache(name);
        if (caffeineCache != null) {
            caffeineCache.evict(key);
            log.info("删除缓存成功，缓存名称：{}，键：{}", name, key);
        }
    }

    /**
     * 获取缓存统计信息
     */
    @Override
    public List<CaffeineStatsVO> stats() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        return cacheNames.stream()
                .map(name -> {
                    CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache(name);
                    if (caffeineCache != null) {
                        Cache<Object, Object> nativeCache = caffeineCache.getNativeCache();
                        // 统计信息
                        CacheStats stats = nativeCache.stats();
                        return getCaffeineStatsVO(name, stats);
                    }
                    return null;
                })
                .toList();
    }

    /**
     * 获取缓存内容列表
     */
    @Override
    public List<CaffeineListVO> list() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        return cacheNames.stream()
                .map(name -> {
                    CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache(name);
                    if (caffeineCache != null) {
                        Cache<Object, Object> nativeCache = caffeineCache.getNativeCache();
                        // 缓存内容转为map集合
                        ConcurrentMap<Object, Object> cacheMap = nativeCache.asMap();
                        List<CaffeineListVO.Cache> caches = new ArrayList<>();
                        if (CollUtil.isNotEmpty(cacheMap)) {
                            // 缓存键值对映射为对象
                            cacheMap.forEach((k, v) -> {
                                CaffeineListVO.Cache cache = new CaffeineListVO.Cache();
                                cache.setKey(k);
                                cache.setValue(v);
                                caches.add(cache);
                            });
                        }
                        CaffeineListVO caffeineListVO = new CaffeineListVO();
                        caffeineListVO.setName(name);
                        caffeineListVO.setCaches(caches);
                        return caffeineListVO;
                    }
                    return null;
                })
                .toList();
    }


    /**
     * 组装Caffeine统计信息对象
     *
     * @param name  缓存名称
     * @param stats 统计信息
     */
    private static CaffeineStatsVO getCaffeineStatsVO(String name, CacheStats stats) {
        CaffeineStatsVO caffeineStatsVO = new CaffeineStatsVO();
        caffeineStatsVO.setName(name);
        caffeineStatsVO.setHitCount(stats.hitCount());
        caffeineStatsVO.setMissCount(stats.missCount());
        caffeineStatsVO.setLoadSuccessCount(stats.loadSuccessCount());
        caffeineStatsVO.setLoadFailureCount(stats.loadFailureCount());
        caffeineStatsVO.setTotalLoadTime(stats.totalLoadTime());
        caffeineStatsVO.setEvictionCount(stats.evictionCount());
        caffeineStatsVO.setEvictionWeight(stats.evictionWeight());
        return caffeineStatsVO;
    }
}
