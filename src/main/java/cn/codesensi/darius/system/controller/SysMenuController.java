package cn.codesensi.darius.system.controller;

import cn.codesensi.darius.common.annotation.ApiResponseBody;
import cn.codesensi.darius.common.annotation.OperateLog;
import cn.codesensi.darius.common.base.BaseController;
import cn.codesensi.darius.common.enums.OperateType;
import cn.codesensi.darius.system.entity.SysMenu;
import cn.codesensi.darius.system.service.ISysMenuService;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单信息表 前端控制器
 *
 * @author codesensi
 * @since 2024-05-15 22:09:56
 */
@Slf4j
@ApiResponseBody
@RestController
@Tag(name = "菜单信息表接口", description = "菜单信息表接口")
@RequestMapping("/sys/sys-menu")
public class SysMenuController extends BaseController {

    @Resource
    private ISysMenuService sysMenuService;

    /**
     * 新增
     *
     * @param sysMenu 实体类
     */
    @OperateLog(operateType = OperateType.INSERT, description = "新增一条菜单信息表数据")
    @ApiOperationSupport(order = 1)
    @Operation(summary = "新增")
    @PostMapping("/save")
    public void save(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
    }

    /**
     * 删除
     *
     * @param id 主键id
     */
    @OperateLog(operateType = OperateType.DELETE, description = "根据id删除一条菜单信息表数据")
    @ApiOperationSupport(order = 2)
    @Operation(summary = "删除")
    @Parameter(name = "id", description = "主键id", required = true, in = ParameterIn.PATH)
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        sysMenuService.removeById(id);
    }

    /**
     * 更新
     *
     * @param sysMenu 实体类
     */
    @OperateLog(operateType = OperateType.UPDATE, description = "根据id更新一条菜单信息表数据")
    @ApiOperationSupport(order = 3)
    @Operation(summary = "更新")
    @PutMapping("/update")
    public void update(@RequestBody SysMenu sysMenu) {
        // 乐观锁更新
        SysMenu info = sysMenuService.getById(sysMenu.getId());
        if (ObjUtil.isNotNull(info)) {
            Integer version = info.getVersion();
            sysMenu.setVersion(version);
        }
        sysMenuService.updateById(sysMenu);
    }

    /**
     * 分页列表
     *
     * @param current 当前页
     * @param size    每页数量
     * @param sysMenu 实体类
     * @return PageInfo<SysMenu> 分页对象
     */
    // @OperateLog(operateType = OperateType.QUERY, description = "条件查询菜单信息表分页列表", isSaveResponseData = false)
    @ApiOperationSupport(order = 4)
    @Operation(summary = "分页列表")
    @Parameters({
            @Parameter(name = "current", description = "当前页", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "每页数量", required = true, in = ParameterIn.QUERY)
    })
    @GetMapping("/page")
    public Page<SysMenu> page(@RequestParam(name = "current", defaultValue = "1") Integer current,
                              @RequestParam(name = "size", defaultValue = "10") Integer size,
                              @ParameterObject SysMenu sysMenu) {
        Page<SysMenu> page = new Page<>(current, size);
        return sysMenuService.lambdaQuery()
                // TODO 组织条件
                .eq(ObjUtil.isNotNull(sysMenu.getId()), SysMenu::getId, sysMenu.getId())
                .orderByDesc(SysMenu::getCreateTime)
                .page(page);
    }

    /**
     * 详情
     *
     * @param id 主键id
     * @return SysMenu 实体类
     */
    // @OperateLog(operateType = OperateType.QUERY, description = "根据id查询菜单信息表详情")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "详情")
    @Parameter(name = "id", description = "主键id", required = true, in = ParameterIn.PATH)
    @GetMapping("/detail/{id}")
    public SysMenu detail(@PathVariable(name = "id") Long id) {
        return sysMenuService.getById(id);
    }

    /**
     * 测试-查询用户菜单权限码
     */
    @Operation(summary = "测试-查询用户菜单权限码")
    @Parameter(name = "userId", description = "用户id", required = true, in = ParameterIn.PATH)
    @GetMapping("/list/code/{userId}")
    public List<String> testListMenuCodeByUserId(@PathVariable(name = "userId") Long userId) {
        List<String> menuCodeList = sysMenuService.listMenuCodeByUserId(userId);
        for (String menuCode : menuCodeList) {
            log.info("|-----menuCode:{}|-----", menuCode);
        }
        return menuCodeList;
    }

}
