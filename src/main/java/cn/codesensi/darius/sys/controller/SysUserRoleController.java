package cn.codesensi.darius.sys.controller;

import cn.codesensi.darius.common.annotation.ApiResponseBody;
import cn.codesensi.darius.common.annotation.OperateLog;
import cn.codesensi.darius.common.base.BaseController;
import cn.codesensi.darius.common.enums.OperateType;
import cn.codesensi.darius.sys.entity.SysUserRole;
import cn.codesensi.darius.sys.service.ISysUserRoleService;
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
 * 用户角色关联表 前端控制器
 *
 * @author codesensi
 * @since 2024-11-30 23:33:21
 */
@ApiResponseBody
@RestController
@Tag(name = "用户角色关联表接口", description = "用户角色关联表接口")
@RequestMapping("/sys/sys-user-role")
public class SysUserRoleController extends BaseController {

    @Resource
    private ISysUserRoleService sysUserRoleService;

    /**
     * 新增
     *
     * @param sysUserRole 实体类
     */
    @OperateLog(operateType = OperateType.INSERT, description = "新增一条用户角色关联表数据")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "新增")
    @PostMapping("/save")
    public void save(@RequestBody SysUserRole sysUserRole) {
        sysUserRoleService.save(sysUserRole);
    }

    /**
     * 删除
     *
     * @param id 主键id
     */
    @OperateLog(operateType = OperateType.DELETE, description = "根据id删除一条用户角色关联表数据")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "删除")
    @Parameter(name = "id", description = "主键id", required = true, in = ParameterIn.PATH)
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        sysUserRoleService.removeById(id);
    }

    /**
     * 更新
     *
     * @param sysUserRole 实体类
     */
    @OperateLog(operateType = OperateType.UPDATE, description = "根据id更新一条用户角色关联表数据")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "更新")
    @PutMapping("/update")
    public void update(@RequestBody SysUserRole sysUserRole) {
        // 乐观锁更新
        SysUserRole info = sysUserRoleService.getById(sysUserRole.getId());
        if (ObjUtil.isNotNull(info)) {
            Integer version = info.getVersion();
            sysUserRole.setVersion(version);
        }
        sysUserRoleService.updateById(sysUserRole);
    }

    /**
     * 分页列表
     *
     * @param current     当前页
     * @param size        每页数量
     * @param sysUserRole 实体类
     * @return PageInfo<SysUserRole> 分页对象
     */
    // @OperateLog(operateType = OperateType.QUERY, description = "条件查询用户角色关联表分页列表", isSaveResponseData = false)
    @ApiOperationSupport(order = 4)
    @Operation(summary = "分页列表")
    @Parameters({
            @Parameter(name = "current", description = "当前页", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "每页数量", required = true, in = ParameterIn.QUERY)
    })
    @GetMapping("/page")
    public Page<SysUserRole> page(@RequestParam(name = "current", defaultValue = "1") Integer current,
                                  @RequestParam(name = "size", defaultValue = "10") Integer size,
                                  @ParameterObject SysUserRole sysUserRole) {
        Page<SysUserRole> page = new Page<>(current, size);
        return sysUserRoleService.lambdaQuery()
                // TODO 组织条件
                .eq(ObjUtil.isNotNull(sysUserRole.getId()), SysUserRole::getId, sysUserRole.getId())
                .orderByDesc(SysUserRole::getCreateTime)
                .page(page);
    }

    /**
     * 详情
     *
     * @param id 主键id
     * @return SysUserRole 实体类
     */
    // @OperateLog(operateType = OperateType.QUERY, description = "根据id查询用户角色关联表详情")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "详情")
    @Parameter(name = "id", description = "主键id", required = true, in = ParameterIn.PATH)
    @GetMapping("/detail/{id}")
    public SysUserRole detail(@PathVariable(name = "id") Long id) {
        return sysUserRoleService.getById(id);
    }

}
