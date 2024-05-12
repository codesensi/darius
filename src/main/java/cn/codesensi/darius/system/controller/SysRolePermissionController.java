package cn.codesensi.darius.system.controller;

import cn.codesensi.darius.common.annotation.ApiResponseBody;
import cn.codesensi.darius.common.annotation.OperateLog;
import cn.codesensi.darius.common.enums.OperateType;
import cn.codesensi.darius.system.entity.SysRolePermission;
import cn.codesensi.darius.system.service.ISysRolePermissionService;
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
 * 角色权限关联表 前端控制器
 *
 * @author codesensi
 * @since 2024-05-12 19:57:32
 */
@ApiResponseBody
@RestController
@Tag(name = "角色权限关联表接口", description = "角色权限关联表接口")
@RequestMapping("/system/sys-role-permission")
public class SysRolePermissionController {

    @Resource
    private ISysRolePermissionService sysRolePermissionService;

    /**
     * 新增
     *
     * @param sysRolePermission 实体类
     */
    @OperateLog(operateType = OperateType.INSERT, description = "新增一条sysRolePermission数据")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "新增")
    @PostMapping("/save")
    public void save(@RequestBody SysRolePermission sysRolePermission) {
        sysRolePermissionService.save(sysRolePermission);
    }

    /**
     * 删除
     *
     * @param id 主键id
     */
    @OperateLog(operateType = OperateType.DELETE, description = "根据id删除一条sysRolePermission数据")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "删除")
    @Parameter(name = "id", description = "主键id", required = true, in = ParameterIn.PATH)
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        sysRolePermissionService.removeById(id);
    }

    /**
     * 更新
     *
     * @param sysRolePermission 实体类
     */
    @OperateLog(operateType = OperateType.UPDATE, description = "根据id更新一条sysRolePermission数据")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "更新")
    @PutMapping("/update")
    public void update(@RequestBody SysRolePermission sysRolePermission) {
        // 乐观锁更新
        SysRolePermission info = sysRolePermissionService.getById(sysRolePermission.getId());
        if (ObjUtil.isNotNull(info)) {
            Integer version = info.getVersion();
            sysRolePermission.setVersion(version);
        }
        sysRolePermissionService.updateById(sysRolePermission);
    }

    /**
     * 分页列表
     *
     * @param current           当前页
     * @param size              每页数量
     * @param sysRolePermission 实体类
     * @return PageInfo<SysRolePermission> 分页对象
     */
    // @OperateLog(operateType = OperateType.QUERY, description = "条件查询sysRolePermission分页列表", isSaveResponseData = false)
    @ApiOperationSupport(order = 4)
    @Operation(summary = "分页列表")
    @Parameters({
            @Parameter(name = "current", description = "当前页", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "每页数量", required = true, in = ParameterIn.QUERY)
    })
    @GetMapping("/page")
    public Page<SysRolePermission> page(@RequestParam(name = "current", defaultValue = "1") Integer current,
                                        @RequestParam(name = "size", defaultValue = "10") Integer size,
                                        @ParameterObject SysRolePermission sysRolePermission) {
        Page<SysRolePermission> page = new Page<>(current, size);
        return sysRolePermissionService.lambdaQuery()
                // TODO 组织条件
                .eq(ObjUtil.isNotNull(sysRolePermission.getId()), SysRolePermission::getId, sysRolePermission.getId())
                .orderByDesc(SysRolePermission::getCreateTime)
                .page(page);
    }

    /**
     * 详情
     *
     * @param id 主键id
     * @return SysRolePermission 实体类
     */
    // @OperateLog(operateType = OperateType.QUERY, description = "根据id查询sysRolePermission详情")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "详情")
    @Parameter(name = "id", description = "主键id", required = true, in = ParameterIn.PATH)
    @GetMapping("/detail/{id}")
    public SysRolePermission detail(@PathVariable(name = "id") Long id) {
        return sysRolePermissionService.getById(id);
    }

}
