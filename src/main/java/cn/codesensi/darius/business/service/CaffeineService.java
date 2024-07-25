package cn.codesensi.darius.business.service;

import cn.codesensi.darius.business.vo.CaffeineListVO;
import cn.codesensi.darius.business.vo.CaffeineStatsVO;

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
     * 获取缓存统计信息
     */
    List<CaffeineStatsVO> stats();

    /**
     * 获取缓存内容列表
     */
    List<CaffeineListVO> list();
}
