package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
<#if springdoc>
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
</#if>
import cn.codesensi.darius.common.annotation.ApiResponseBody;
import cn.codesensi.darius.common.annotation.OperateLog;
import cn.codesensi.darius.common.enums.OperateType;
import ${package.Entity}.${entity};
import org.springdoc.core.annotations.ParameterObject;
import jakarta.annotation.Resource;
import ${package.Service}.${table.serviceName};
import org.springframework.web.bind.annotation.*;

/**
 * ${table.comment!} 前端控制器
 *
 * @author ${author}
 * @since ${date}
 */
@ApiResponseBody
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
<#if springdoc>
@Tag(name = "${table.comment!}接口", description = "${table.comment!}接口")
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    @Resource
    private ${table.serviceName} ${table.entityPath}Service;

    /**
     * 新增
     *
     * @param ${table.entityPath} 实体类
     */
    @OperateLog(operateType = OperateType.INSERT, description = "新增一条${table.entityPath}数据")
<#if springdoc>
    @ApiOperationSupport(order = 1)
    @Operation(summary = "新增")
</#if>
    @PostMapping("/save")
    public void save(@RequestBody ${entity} ${table.entityPath}) {
        ${table.entityPath}Service.save(${table.entityPath});
    }

    /**
     * 删除
     *
     * @param id 主键id
     */
    @OperateLog(operateType = OperateType.DELETE, description = "根据id删除一条${table.entityPath}数据")
<#if springdoc>
    @ApiOperationSupport(order = 2)
    @Operation(summary = "删除")
    @Parameter(name = "id", description = "主键id", required = true, in = ParameterIn.PATH)
</#if>
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long id) {
        ${table.entityPath}Service.removeById(id);
    }

    /**
     * 更新
     *
     * @param ${table.entityPath} 实体类
     */
    @OperateLog(operateType = OperateType.UPDATE, description = "根据id更新一条${table.entityPath}数据")
<#if springdoc>
    @ApiOperationSupport(order = 3)
    @Operation(summary = "更新")
</#if>
    @PutMapping("/update")
    public void update(@RequestBody ${entity} ${table.entityPath}) {
        // 乐观锁更新
        ${entity} info = ${table.entityPath}Service.getById(${table.entityPath}.getId());
        if (ObjUtil.isNotNull(info)) {
            Integer version = info.getVersion();
            ${table.entityPath}.setVersion(version);
        }
        ${table.entityPath}Service.updateById(${table.entityPath});
    }

    /**
     * 分页列表
     *
     * @param current 当前页
     * @param size 每页数量
     * @param ${table.entityPath} 实体类
     * @return PageInfo<${entity}> 分页对象
     */
    // @OperateLog(operateType = OperateType.QUERY, description = "条件查询${table.entityPath}分页列表", isSaveResponseData = false)
<#if springdoc>
    @ApiOperationSupport(order = 4)
    @Operation(summary = "分页列表")
    @Parameters({
            @Parameter(name = "current", description = "当前页", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "size", description = "每页数量", required = true, in = ParameterIn.QUERY)
    })
</#if>
    @GetMapping("/page")
    public Page<${entity}> page(@RequestParam(name = "current", defaultValue = "1") Integer current,
                                    @RequestParam(name = "size", defaultValue = "10") Integer size,
                                    @ParameterObject ${entity} ${table.entityPath}) {
        Page<${entity}> page = new Page<>(current, size);
        return ${table.entityPath}Service.lambdaQuery()
            // TODO 组织条件
            .eq(ObjUtil.isNotNull(${table.entityPath}.getId()), ${entity}::getId, ${table.entityPath}.getId())
            .orderByDesc(${entity}::getCreateTime)
            .page(page);
    }

    /**
     * 详情
     *
     * @param id 主键id
     * @return ${entity} 实体类
     */
    // @OperateLog(operateType = OperateType.QUERY, description = "根据id查询${table.entityPath}详情")
<#if springdoc>
    @ApiOperationSupport(order = 5)
    @Operation(summary = "详情")
    @Parameter(name = "id", description = "主键id", required = true, in = ParameterIn.PATH)
</#if>
    @GetMapping("/detail/{id}")
    public ${entity} detail(@PathVariable(name = "id") Long id) {
        return ${table.entityPath}Service.getById(id);
    }

}
</#if>
