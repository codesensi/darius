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
import java.time.LocalDateTime;

/**
 * 登录日志表 实体类
 *
 * @author codesensi
 * @since 2024-08-10 21:26:37
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_login_log")
@Schema(name = "SysLoginLog", description = "登录日志表")
public class SysLoginLog extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @Schema(description = "日志ID")
    private Long id;

    /**
     * 登录登出类型:0-未知,1-登录,2-登出
     */
    @Schema(description = "登录登出类型:0-未知,1-登录,2-登出")
    private Integer loginType;

    /**
     * 登录方式:0-未知,1-账号密码,2-手机验证码
     */
    @Schema(description = "登录方式:0-未知,1-账号密码,2-手机验证码")
    private Integer loginMode;

    /**
     * 登录登出时间
     */
    @Schema(description = "登录登出时间")
    private LocalDateTime loginTime;

    /**
     * 登录登出IP
     */
    @Schema(description = "登录登出IP")
    private String loginIp;

    /**
     * 登录登出地区
     */
    @Schema(description = "登录登出地区")
    private String loginArea;

    /**
     * 登录登出系统
     */
    @Schema(description = "登录登出系统")
    private String loginOs;

    /**
     * 登录登出设备
     */
    @Schema(description = "登录登出设备")
    private String loginDevice;

    /**
     * 登录登出浏览器
     */
    @Schema(description = "登录登出浏览器")
    private String loginBrowser;

    /**
     * 登录登出状态:0-失败,1-成功
     */
    @Schema(description = "登录登出状态:0-失败,1-成功")
    private Integer loginState;

    /**
     * 登录登出失败原因
     */
    @Schema(description = "登录登出失败原因")
    private String loginFailReason;
}
