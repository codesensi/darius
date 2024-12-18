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
 * 角色信息表 实体类
 *
 * @author codesensi
 * @since 2024-11-30 23:33:21
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
@Schema(name = "SysRole", description = "角色信息表")
public class SysRole extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @Schema(description = "角色ID")
    private Long id;

    /**
     * 角色名称
     */
    @Schema(description = "角色名称")
    private String name;

    /**
     * 角色编码
     */
    @Schema(description = "角色编码")
    private String code;

    /**
     * 父角色ID
     */
    @Schema(description = "父角色ID")
    private Long pid;

    /**
     * 角色描述
     */
    @Schema(description = "角色描述")
    private String description;

    /**
     * 角色类型:0-默认角色
     */
    @Schema(description = "角色类型:0-默认角色")
    private Integer type;

    /**
     * 角色排序
     */
    @Schema(description = "角色排序")
    private Integer sort;

    /**
     * 角色状态:0-正常,1-禁用
     */
    @Schema(description = "角色状态:0-正常,1-禁用")
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
}
