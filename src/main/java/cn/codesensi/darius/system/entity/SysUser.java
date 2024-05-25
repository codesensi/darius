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
 * 用户信息表 实体类
 *
 * @author codesensi
 * @since 2024-05-15 22:08:36
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
@Schema(name = "SysUser", description = "用户信息表")
public class SysUser extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long id;

    /**
     * 用户名称
     */
    @Schema(description = "用户名称")
    private String username;

    /**
     * 用户密码
     */
    @Schema(description = "用户密码")
    private String password;

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    private String nickname;

    /**
     * 用户身份证号码
     */
    @Schema(description = "用户身份证号码")
    private String idno;

    /**
     * 用户邮箱
     */
    @Schema(description = "用户邮箱")
    private String email;

    /**
     * 用户手机号码
     */
    @Schema(description = "用户手机号码")
    private String phone;

    /**
     * 用户性别:0-保密,1-男,2-女
     */
    @Schema(description = "用户性别:0-保密,1-男,2-女")
    private Integer gender;

    /**
     * 用户头像地址
     */
    @Schema(description = "用户头像地址")
    private String avatar;

    /**
     * 用户类型:0-系统用户
     */
    @Schema(description = "用户类型:0-系统用户")
    private Integer type;

    /**
     * 用户状态:0-启用,1-禁用
     */
    @Schema(description = "用户状态:0-启用,1-禁用")
    private Integer status;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;
}
