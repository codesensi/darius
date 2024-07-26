package cn.codesensi.darius.business.controller;

import cn.codesensi.darius.business.service.CaffeineService;
import cn.codesensi.darius.business.vo.CaffeineListVO;
import cn.codesensi.darius.business.vo.CaffeineStatsVO;
import cn.codesensi.darius.common.annotation.ApiResponseBody;
import cn.codesensi.darius.common.response.R;
import cn.codesensi.darius.common.response.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Caffeine缓存 前端控制器
 *
 * @author codesensi
 * @since 2024-07-21 11:09:56
 */
@ApiResponseBody
@RestController
@Tag(name = "Caffeine缓存接口", description = "Caffeine缓存接口")
@RequestMapping("/caffeine")
public class CaffeineController {

    @Resource
    private CaffeineService caffeineService;

    /**
     * 获取缓存值
     */
    @Operation(summary = "获取缓存值")
    @Parameters({
            @Parameter(name = "name", description = "缓存名称", required = true, in = ParameterIn.QUERY),
            @Parameter(name = "key", description = "缓存键", required = true, in = ParameterIn.QUERY)
    })
    @GetMapping("/get")
    public Result<Object> get(@RequestParam(name = "name") String name, @RequestParam(name = "key") Object key) {
        return R.ok(caffeineService.get(name, key));
    }

    /**
     * 获取缓存统计信息
     */
    @Operation(summary = "获取缓存统计信息")
    @GetMapping("/stats")
    public List<CaffeineStatsVO> stats() {
        return caffeineService.stats();
    }

    /**
     * 获取缓存内容列表
     */
    @Operation(summary = "获取缓存内容列表")
    @GetMapping("/list")
    public List<CaffeineListVO> list() {
        return caffeineService.list();
    }

}
