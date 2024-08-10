package cn.codesensi.darius.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 缓存内容结果参数
 */
@Data
@Accessors(chain = true)
@Schema(name = "缓存内容结果参数", description = "缓存内容结果参数")
public class CaffeineListVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 缓存名称
     */
    @Schema(description = "缓存名称")
    private String name;

    /**
     * 缓存内容
     */
    @Schema(description = "缓存内容列表")
    private List<Cache> caches;

    /**
     * 缓存内容对象
     */
    @Data
    @Accessors(chain = true)
    @Schema(name = "缓存内容对象", description = "缓存内容对象")
    public static class Cache {

        /**
         * 缓存键
         */
        @Schema(description = "缓存key")
        private Object key;

        /**
         * 缓存值
         */
        @Schema(description = "缓存内容")
        private Object value;
    }
}
