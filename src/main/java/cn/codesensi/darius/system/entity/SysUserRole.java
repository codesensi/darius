package cn.codesensi.darius.system.entity;

import cn.codesensi.darius.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户角色关联表 实体类
 *
 * @author codesensi
 * @since 2024-05-12 19:57:32
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user_role")
@Schema(name = "SysUserRole", description = "用户角色关联表")
public class SysUserRole extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private Long roleId;
}
