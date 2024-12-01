package cn.codesensi.darius.sys.entity;

import cn.codesensi.darius.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.io.Serial;

/**
 * 权限信息表 实体类
 *
 * @author codesensi
 * @since 2024-11-30 23:33:21
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
     * 权限编码
     */
    @Schema(description = "权限编码")
    private String code;

    /**
     * 父权限ID
     */
    @Schema(description = "父权限ID")
    private Long pid;

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
     * 权限排序
     */
    @Schema(description = "权限排序")
    private Integer sort;

    /**
     * 权限图标
     */
    @Schema(description = "权限图标")
    private String icon;

    /**
     * 权限路由名称
     */
    @Schema(description = "权限路由名称")
    private String routeName;

    /**
     * 权限路由地址
     */
    @Schema(description = "权限路由地址")
    private String routePath;

    /**
     * 权限组件路径
     */
    @Schema(description = "权限组件路径")
    private String componentPath;

    /**
     * 权限状态:0-正常,1-禁用
     */
    @Schema(description = "权限状态:0-正常,1-禁用")
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
}
