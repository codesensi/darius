package cn.codesensi.darius.api.service;

import cn.codesensi.darius.api.vo.CaffeineListVO;
import cn.codesensi.darius.api.vo.CaffeineStatsVO;

import java.util.List;

/**
 * Caffeine缓存接口
 */
public interface CaffeineService {

    /**
     * 获取缓存值
     *
     * @param name 缓存名称
     * @param key  缓存键
     */
    Object get(String name, Object key);

    /**
     * 放入缓存
     *
     * @param name   缓存名称
     * @param key    缓存键
     * @param value  缓存值
     * @param expire 过期秒数
     */
    void put(String name, Object key, Object value, Long expire);

    /**
     * 删除缓存
     *
     * @param name 缓存名称
     * @param key  缓存键
     */
    void evict(String name, Object key);

    /**
     * 获取缓存统计信息
     */
    List<CaffeineStatsVO> stats();

    /**
     * 获取缓存内容列表
     */
    List<CaffeineListVO> list();
}