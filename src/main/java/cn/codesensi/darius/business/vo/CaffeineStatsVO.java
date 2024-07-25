package cn.codesensi.darius.business.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * Caffeine信息统计对象
 */
@Data
@Accessors(chain = true)
@Schema(name = "Caffeine信息统计对象", description = "Caffeine信息统计对象")
public class CaffeineStatsVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 缓存名称
     */
    @Schema(description = "缓存名称")
    private String name;

    /**
     * 命中次数
     */
    @Schema(description = "命中次数")
    private Long hitCount;

    /**
     * 未命中次数
     */
    @Schema(description = "未命中次数")
    private Long missCount;

    /**
     * 成功加载次数
     */
    @Schema(description = "成功加载次数")
    private Long loadSuccessCount;

    /**
     * 失败加载次数
     */
    @Schema(description = "失败加载次数")
    private Long loadFailureCount;

    /**
     * 加载总耗时
     */
    @Schema(description = "加载总耗时")
    private Long totalLoadTime;

    /**
     * 逐出次数
     */
    @Schema(description = "逐出次数")
    private Long evictionCount;

    /**
     * 逐出的权重（缓存大小单位）
     * evictionWeight是一个描述缓存中每个条目被逐出的相对权重的指标，以缓存大小的单位来衡量。它是缓存算法中用于确定哪些条目应该被优先逐出的一个重要因素。
     * 当缓存空间不足时，缓存算法会根据条目的evictionWeight来做出决策。较高的evictionWeight意味着条目更有可能被优先逐出，因为它们对缓存空间的使用量更大。
     * evictionWeight的计算方式可以因不同的缓存实现而异，通常会考虑条目的大小、访问频率、时间戳等因素。较大的evictionWeight值通常表示该条目在缓存中的重要性更低，可以被更容易地逐出。
     * 通过调整evictionWeight，可以对缓存算法进行优化，以便更好地适应特定的应用场景和需求。
     */
    @Schema(description = "逐出的权重")
    private Long evictionWeight;
}
