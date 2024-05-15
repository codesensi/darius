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
 * 菜单信息表 实体类
 *
 * @author codesensi
 * @since 2024-05-15 22:09:56
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
@Schema(name = "SysMenu", description = "菜单信息表")
public class SysMenu extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @Schema(description = "菜单ID")
    private Long id;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String name;

    /**
     * 菜单权限码
     */
    @Schema(description = "菜单权限码")
    private String code;

    /**
     * 父菜单ID
     */
    @Schema(description = "父菜单ID")
    private Long pid;

    /**
     * 菜单描述
     */
    @Schema(description = "菜单描述")
    private String description;

    /**
     * 菜单类型:1-目录,2-菜单,3-按钮
     */
    @Schema(description = "菜单类型:1-目录,2-菜单,3-按钮")
    private Integer type;

    /**
     * 菜单排序
     */
    @Schema(description = "菜单排序")
    private Integer sort;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String icon;

    /**
     * 菜单路由名称
     */
    @Schema(description = "菜单路由名称")
    private String routeName;

    /**
     * 菜单路由地址
     */
    @Schema(description = "菜单路由地址")
    private String routePath;

    /**
     * 菜单状态:0-正常,1-禁用
     */
    @Schema(description = "菜单状态:0-正常,1-禁用")
    private Integer state;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
}
