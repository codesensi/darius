package cn.codesensi.darius.business.controller;

import cn.codesensi.darius.business.vo.CaffeineStatsVO;
import cn.codesensi.darius.common.annotation.ApiResponseBody;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

/**
 * 缓存 前端控制器
 *
 * @author codesensi
 * @since 2024-07-21 11:09:56
 */
@ApiResponseBody
@RestController
@Tag(name = "缓存接口", description = "缓存接口")
@RequestMapping("/cache")
public class CacheController {

    @Resource
    private CacheManager cacheManager;

    /**
     * Caffeine缓存统计信息
     */
    @Operation(summary = "Caffeine缓存统计信息")
    @GetMapping("/caffeine/stats")
    public List<CaffeineStatsVO> caffeineStats() {
        Collection<String> cacheNames = cacheManager.getCacheNames();
        return cacheNames.stream()
                .map(cacheName -> {
                    CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache(cacheName);
                    if (caffeineCache != null) {
                        Cache<Object, Object> cache = caffeineCache.getNativeCache();
                        CacheStats stats = cache.stats();
                        return getCaffeineStatsVO(cacheName, stats);
                    }
                    return null;
                })
                .toList();
    }

    /**
     * 组装Caffeine信息统计对象
     *
     * @param cacheName 缓存名称
     * @param stats     统计信息
     */
    private static CaffeineStatsVO getCaffeineStatsVO(String cacheName, CacheStats stats) {
        CaffeineStatsVO caffeineStatsVO = new CaffeineStatsVO();
        caffeineStatsVO.setCacheName(cacheName);
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
