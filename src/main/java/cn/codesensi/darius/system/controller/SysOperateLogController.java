package cn.codesensi.darius.system.controller;

import cn.codesensi.darius.common.annotation.ApiResponseBody;
import cn.codesensi.darius.common.annotation.OperateLog;
import cn.codesensi.darius.common.enums.OperateType;
import cn.codesensi.darius.system.entity.SysOperateLog;
import cn.codesensi.darius.system.service.ISysOperateLogService;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

/**
 * 操作日志表 前端控制器
 *
 * @author codesensi
 * @since 2024-05-15 22:08:36
 */
@ApiResponseBody
@RestController
@Tag(name = "操作日志表接口", description = "操作日志表接口")
@RequestMapping("/system/sys-operate-log")
public class SysOperateLogController {

    @Resource
    private ISysOperateLogService sysOperateLogService;

    /**
     * 新增
     *
     * @param sysOperateLog 实体类
     */
    @OperateLog(operateType = OperateType.INSERT, description = "新增一条sysOperateLog数据")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "新增")
    @PostMapping("/save")
    public void save(@RequestBody SysOperateLog sysOperateLog) {
        sysOperateLogService.save(sysOperateLog);
    }

    /**
     * 删除
     *
     * @param id 主键id
     */
    @OperateLog(operateType = OperateType.DELETE, description = "根据id删除一条sysOperateLog数据")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "删除")
    @Parameter(name = "id", description = "主键id", required = true, in = ParameterIn.PATH)
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        sysOperateLogService.removeById(id);
    }

    /**
     * 更新
     *
     * @param sysOperateLog 实体类
     */
    @OperateLog(operateType = OperateType.UPDATE, description = "根据id更新一条sysOperateLog数据")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "更新")
    @PutMapping("/update")
    public void update(@RequestBody SysOperateLog sysOperateLog) {
        // 乐观锁更新
        SysOperateLog info = sysOperateLogService.getById(sysOperateLog.getId());
        if (ObjUtil.isNotNull(info)) {
            Integer version = info.getVersion();
            sysOperateLog.setVersion(version);
        }
        sysOperateLogService.updateById(sysOperateLog);
    }

    /**
     * 分页列表
     *
     * @param current       当前页
     * @param size          每页数量
     * @param sysOperateLog 实体类
     * @return PageInfo<SysOperateLog> 分页对象
     */
    // @OperateLog(operateType = OperateType.QUERY, description = "条件查询sysOperateLog分页列表", isSaveResponseData = false)
    @ApiOperationSupport(order = 4)
    @Operation(summary = "分页列表")
    @Parameters({
            @Parameter(name = "current", description = "当前页", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "每页数量", required = true, in = ParameterIn.QUERY)
    })
    @GetMapping("/page")
    public Page<SysOperateLog> page(@RequestParam(name = "current", defaultValue = "1") Integer current,
                                    @RequestParam(name = "size", defaultValue = "10") Integer size,
                                    @ParameterObject SysOperateLog sysOperateLog) {
        Page<SysOperateLog> page = new Page<>(current, size);
        return sysOperateLogService.lambdaQuery()
                // TODO 组织条件
                .eq(ObjUtil.isNotNull(sysOperateLog.getId()), SysOperateLog::getId, sysOperateLog.getId())
                .orderByDesc(SysOperateLog::getCreateTime)
                .page(page);
    }

    /**
     * 详情
     *
     * @param id 主键id
     * @return SysOperateLog 实体类
     */
    // @OperateLog(operateType = OperateType.QUERY, description = "根据id查询sysOperateLog详情")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "详情")
    @Parameter(name = "id", description = "主键id", required = true, in = ParameterIn.PATH)
    @GetMapping("/detail/{id}")
    public SysOperateLog detail(@PathVariable(name = "id") Long id) {
        return sysOperateLogService.getById(id);
    }

}
