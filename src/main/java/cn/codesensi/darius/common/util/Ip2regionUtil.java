package cn.codesensi.darius.common.util;

import cn.hutool.core.io.resource.ResourceUtil;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.IOException;

/**
 * ip2region工具类
 *
 * @author codesensi
 * @since 2024/1/12 22:48
 */

@Slf4j
public class Ip2regionUtil {

    /**
     * 初始化 SEARCHER
     */
    @SuppressWarnings("InstantiationOfUtilityClass")
    private final static Ip2regionUtil IP_2_REGION_UTIL = new Ip2regionUtil();

    /**
     * 启动加载到内存中
     */
    private static Searcher SEARCHER;

    /**
     * 私有化构造
     */
    private Ip2regionUtil() {
        try {
            byte[] bytes = ResourceUtil.readBytes("ip2region/ip2region.xdb");
            SEARCHER = Searcher.newWithBuffer(bytes);
        } catch (IOException ignored) {
        }
    }

    /**
     * 查询IP对应的地区
     */
    public static String search(String ip) {
        try {
            return SEARCHER.search(ip);
        } catch (Exception ignored) {
        }
        return "未知";
    }

}
