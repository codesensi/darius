package cn.codesensi.darius.system.controller;

import cn.codesensi.darius.common.annotation.ApiResponseBody;
import cn.codesensi.darius.common.annotation.OperateLog;
import cn.codesensi.darius.common.base.BaseController;
import cn.codesensi.darius.common.enums.OperateType;
import cn.codesensi.darius.system.entity.SysConfig;
import cn.codesensi.darius.system.service.ISysConfigService;
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
 * 系统配置表 前端控制器
 *
 * @author codesensi
 * @since 2024-11-30 20:49:47
 */
@ApiResponseBody
@RestController
@Tag(name = "系统配置表接口", description = "系统配置表接口")
@RequestMapping("/sys/sys-config")
public class SysConfigController extends BaseController {

    @Resource
    private ISysConfigService sysConfigService;

    /**
     * 新增
     *
     * @param sysConfig 实体类
     */
    @OperateLog(operateType = OperateType.INSERT, description = "新增一条系统配置表数据")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "新增")
    @PostMapping("/save")
    public void save(@RequestBody SysConfig sysConfig) {
        sysConfigService.save(sysConfig);
    }

    /**
     * 删除
     *
     * @param id 主键id
     */
    @OperateLog(operateType = OperateType.DELETE, description = "根据id删除一条系统配置表数据")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "删除")
    @Parameter(name = "id", description = "主键id", required = true, in = ParameterIn.PATH)
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        sysConfigService.removeById(id);
    }

    /**
     * 更新
     *
     * @param sysConfig 实体类
     */
    @OperateLog(operateType = OperateType.UPDATE, description = "根据id更新一条系统配置表数据")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "更新")
    @PutMapping("/update")
    public void update(@RequestBody SysConfig sysConfig) {
        // 乐观锁更新
        SysConfig info = sysConfigService.getById(sysConfig.getId());
        if (ObjUtil.isNotNull(info)) {
            Integer version = info.getVersion();
            sysConfig.setVersion(version);
        }
        sysConfigService.updateById(sysConfig);
    }

    /**
     * 分页列表
     *
     * @param current   当前页
     * @param size      每页数量
     * @param sysConfig 实体类
     * @return PageInfo<SysConfig> 分页对象
     */
    // @OperateLog(operateType = OperateType.QUERY, description = "条件查询系统配置表分页列表", isSaveResponseData = false)
    @ApiOperationSupport(order = 4)
    @Operation(summary = "分页列表")
    @Parameters({
            @Parameter(name = "current", description = "当前页", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "每页数量", required = true, in = ParameterIn.QUERY)
    })
    @GetMapping("/page")
    public Page<SysConfig> page(@RequestParam(name = "current", defaultValue = "1") Integer current,
                                @RequestParam(name = "size", defaultValue = "10") Integer size,
                                @ParameterObject SysConfig sysConfig) {
        Page<SysConfig> page = new Page<>(current, size);
        return sysConfigService.lambdaQuery()
                // TODO 组织条件
                .eq(ObjUtil.isNotNull(sysConfig.getId()), SysConfig::getId, sysConfig.getId())
                .orderByDesc(SysConfig::getCreateTime)
                .page(page);
    }

    /**
     * 详情
     *
     * @param id 主键id
     * @return SysConfig 实体类
     */
    // @OperateLog(operateType = OperateType.QUERY, description = "根据id查询系统配置表详情")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "详情")
    @Parameter(name = "id", description = "主键id", required = true, in = ParameterIn.PATH)
    @GetMapping("/detail/{id}")
    public SysConfig detail(@PathVariable(name = "id") Long id) {
        return sysConfigService.getById(id);
    }

}
