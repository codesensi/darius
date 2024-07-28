package cn.codesensi.darius.system.entity;

import cn.codesensi.darius.common.base.BaseEntity;
import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 授权日志表 实体类
 *
 * @author codesensi
 * @since 2024-07-28 11:16:28
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_auth_log")
@Schema(name = "SysAuthLog", description = "授权日志表")
public class SysAuthLog extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @Schema(description = "日志ID")
    private Long id;

    /**
     * 授权类型:0-未知,1-登录,2-登出,3-封禁
     */
    @Schema(description = "授权类型:0-未知,1-登录,2-登出,3-封禁")
    private Integer authType;

    /**
     * 授权时间
     */
    @Schema(description = "授权时间")
    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN, timezone = "GMT+8")
    private LocalDateTime authTime;

    /**
     * 授权IP
     */
    @Schema(description = "授权IP")
    private String authIp;

    /**
     * 授权地区
     */
    @Schema(description = "授权地区")
    private String authArea;

    /**
     * 授权系统
     */
    @Schema(description = "授权系统")
    private String authOs;

    /**
     * 授权设备
     */
    @Schema(description = "授权设备")
    private String authDevice;

    /**
     * 授权浏览器
     */
    @Schema(description = "授权浏览器")
    private String authBrowser;

    /**
     * 授权状态:0-失败,1-成功
     */
    @Schema(description = "授权状态:0-失败,1-成功")
    private Integer authState;

    /**
     * 授权失败原因
     */
    @Schema(description = "授权失败原因")
    private String authFail;
}
