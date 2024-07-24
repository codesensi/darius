package cn.codesensi.darius.business.service;

/**
 * 缓存接口
 */
public interface CacheService {

    /**
     * 获取缓存内容
     *
     * @param cacheName 缓存名称
     * @param key       缓存key
     */
    Object getValue(String cacheName, String key);

    /**
     * 放入缓存
     *
     * @param cacheName 缓存名称
     * @param key       缓存key
     * @param value     缓存值
     * @param expire    过期秒数
     */
    void put(String cacheName, Object key, Object value, Long expire);
}
