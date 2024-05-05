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
 * 权限信息表 实体类
 *
 * @author codesensi
 * @since 2024-05-05 19:17:53
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_permission")
@Schema(name = "SysPermission", description = "权限信息表")
public class SysPermission extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    @Schema(description = "权限ID")
    private Long id;

    /**
     * 权限名称
     */
    @Schema(description = "权限名称")
    private String name;

    /**
     * 权限标识
     */
    @Schema(description = "权限标识")
    private String perms;

    /**
     * 权限描述
     */
    @Schema(description = "权限描述")
    private String description;

    /**
     * 权限类型:1-目录,2-菜单,3-按钮
     */
    @Schema(description = "权限类型:1-目录,2-菜单,3-按钮")
    private Integer type;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer order;

    /**
     * 图标
     */
    @Schema(description = "图标")
    private String icon;

    /**
     * 父权限ID
     */
    @Schema(description = "父权限ID")
    private Long pid;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

    /**
     * 权限状态:0-正常,1-禁用
     */
    @Schema(description = "权限状态:0-正常,1-禁用")
    private Integer state;
}
