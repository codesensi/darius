package cn.codesensi.darius.system.entity;

import cn.codesensi.darius.common.base.BaseEntity;
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
 * 操作日志表 实体类
 *
 * @author codesensi
 * @since 2024-05-05 14:27:31
 */
@Data
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("sys_operate_log")
@Schema(name = "SysOperateLog", description = "操作日志表")
public class SysOperateLog extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 日志ID
     */
    @Schema(description = "日志ID")
    private Long id;

    /**
     * 操作类型:0-未知,1-登录,2-登出,3-新增,4-更新,5-查询,6-删除
     */
    @Schema(description = "操作类型:0-未知,1-登录,2-登出,3-新增,4-更新,5-查询,6-删除")
    private Integer operateType;

    /**
     * 操作类型说明
     */
    @Schema(description = "操作类型说明")
    private String operateMessage;

    /**
     * 操作描述
     */
    @Schema(description = "操作描述")
    private String operateDescription;

    /**
     * 请求时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "请求时间")
    private LocalDateTime requestTime;

    /**
     * 请求IP
     */
    @Schema(description = "请求IP")
    private String requestIp;

    /**
     * 请求接口
     */
    @Schema(description = "请求接口")
    private String requestUrl;

    /**
     * 请求方法
     */
    @Schema(description = "请求方法")
    private String requestMethod;

    /**
     * 请求方式
     */
    @Schema(description = "请求方式")
    private String requestMode;

    /**
     * 请求参数
     */
    @Schema(description = "请求参数")
    private String requestParam;

    /**
     * 响应状态:0-异常,1-正常
     */
    @Schema(description = "响应状态:0-异常,1-正常")
    private Integer responseState;

    /**
     * 正常响应时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "正常响应时间")
    private LocalDateTime responseTime;

    /**
     * 响应耗时:单位毫秒
     */
    @Schema(description = "响应耗时:单位毫秒")
    private Long responseConsume;

    /**
     * 响应结果
     */
    @Schema(description = "响应结果")
    private String responseResult;

    /**
     * 异常响应时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "异常响应时间")
    private LocalDateTime errorTime;

    /**
     * 异常原因
     */
    @Schema(description = "异常原因")
    private String errorReason;
}
