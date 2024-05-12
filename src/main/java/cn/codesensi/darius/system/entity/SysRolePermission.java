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
 * 角色权限关联表 实体类
 *
 * @author codesensi
 * @since 2024-05-12 19:57:32
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role_permission")
@Schema(name = "SysRolePermission", description = "角色权限关联表")
public class SysRolePermission extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private Long roleId;

    /**
     * 权限ID
     */
    @Schema(description = "权限ID")
    private Long permissionId;
}
