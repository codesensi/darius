package cn.codesensi.darius.common.cache.caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.caffeine.CaffeineCache;

import java.util.concurrent.TimeUnit;

/**
 * Caffeine工具类
 */
public class CaffeineUtil {

    /**
     * 构建Caffeine缓存
     *
     * @param name        缓存名称
     * @param maximumSize 最大存储数量
     * @param expire      过期时间
     */
    public static CaffeineCache buildCaffeineCache(String name, Long maximumSize, Long expire) {
        return new CaffeineCache(name, Caffeine.newBuilder()
                .maximumSize(maximumSize)
                .expireAfterWrite(expire, TimeUnit.SECONDS)
                .recordStats()
                .buildAsync(),
                false);
    }
}
