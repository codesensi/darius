package cn.codesensi.darius.system.controller;

import cn.codesensi.darius.common.annotation.ApiResponseBody;
import cn.codesensi.darius.common.annotation.OperateLog;
import cn.codesensi.darius.common.enums.OperateTypeEnum;
import cn.codesensi.darius.system.entity.SysUser;
import cn.codesensi.darius.system.service.ISysUserService;
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
 * 用户信息表 前端控制器
 *
 * @author codesensi
 * @since 2024-05-05 19:17:53
 */
@ApiResponseBody
@RestController
@Tag(name = "用户信息表接口", description = "用户信息表接口")
@RequestMapping("/system/sys-user")
public class SysUserController {

    @Resource
    private ISysUserService sysUserService;

    /**
     * 新增
     *
     * @param sysUser 实体类
     */
    @OperateLog(operateType = OperateTypeEnum.INSERT, description = "新增一条sysUser数据")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "新增")
    @PostMapping("/save")
    public void save(@RequestBody SysUser sysUser) {
        sysUserService.save(sysUser);
    }

    /**
     * 删除
     *
     * @param id 主键id
     */
    @OperateLog(operateType = OperateTypeEnum.DELETE, description = "根据id删除一条sysUser数据")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "删除")
    @Parameter(name = "id", description = "主键id", required = true, in = ParameterIn.PATH)
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        sysUserService.removeById(id);
    }

    /**
     * 更新
     *
     * @param sysUser 实体类
     */
    @OperateLog(operateType = OperateTypeEnum.UPDATE, description = "根据id更新一条sysUser数据")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "更新")
    @PutMapping("/update")
    public void update(@RequestBody SysUser sysUser) {
        // 乐观锁更新
        SysUser info = sysUserService.getById(sysUser.getId());
        if (ObjUtil.isNotNull(info)) {
            Integer version = info.getVersion();
            sysUser.setVersion(version);
        }
        sysUserService.updateById(sysUser);
    }

    /**
     * 分页列表
     *
     * @param current 当前页
     * @param size    每页数量
     * @param sysUser 实体类
     * @return PageInfo<SysUser> 分页对象
     */
    @OperateLog(operateType = OperateTypeEnum.QUERY, description = "条件查询sysUser分页列表")
    @ApiOperationSupport(order = 4)
    @Operation(summary = "分页列表")
    @Parameters({
            @Parameter(name = "current", description = "当前页", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "每页数量", required = true, in = ParameterIn.QUERY)
    })
    @GetMapping("/page")
    public Page<SysUser> page(@RequestParam(name = "current", defaultValue = "1") Integer current,
                              @RequestParam(name = "size", defaultValue = "10") Integer size,
                              @ParameterObject SysUser sysUser) {
        Page<SysUser> page = new Page<>(current, size);
        return sysUserService.lambdaQuery()
                // TODO 组织条件
                .eq(ObjUtil.isNotNull(sysUser.getId()), SysUser::getId, sysUser.getId())
                .orderByDesc(SysUser::getCreateTime)
                .page(page);
    }

    /**
     * 详情
     *
     * @param id 主键id
     * @return SysUser 实体类
     */
    @OperateLog(operateType = OperateTypeEnum.QUERY, description = "根据id查询sysUser详情")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "详情")
    @Parameter(name = "id", description = "主键id", required = true, in = ParameterIn.PATH)
    @GetMapping("/detail/{id}")
    public SysUser detail(@PathVariable(name = "id") Long id) {
        return sysUserService.getById(id);
    }

}
