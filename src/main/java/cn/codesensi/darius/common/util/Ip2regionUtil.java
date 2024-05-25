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

    private static Searcher SEARCHER;

    /*
      加载到内存中
     */
    static {
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
            String search = SEARCHER.search(ip);
            search = search.replace("|0", "").replace("0|", "");
            if (search.contains("内网")) {
                search = "内网";
            }
            return search;
        } catch (Exception ignored) {
        }
        return "未知";
    }

}
