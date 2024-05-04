package cn.codesensi.darius.common.base;

import com.baomidou.mybatisplus.annotation.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通用实体类
 *
 * @author codesensi
 * @since 2024/1/10 22:44
 */
@Data
@Accessors(chain = true)
@Schema(name = "BaseEntity对象", description = "通用实体类")
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 创建人id
     */
    @Schema(name = "creator", description = "创建人")
    private Long creator;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(name = "createTime", description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新人id
     */
    @Schema(name = "updater", description = "更新人")
    private Long updater;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(name = "updateTime", description = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 删除标识:0-未删除,1-已删除`
     */
    @Schema(name = "deleteFlag", description = "删除标识:0-未删除,1-已删除")
    private Integer deleteFlag;

    /**
     * 乐观锁版本号
     */
    @Schema(name = "version", description = "乐观锁版本号")
    @Version
    private Integer version;

}
