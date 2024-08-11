package cn.codesensi.darius.api.controller;

import cn.codesensi.darius.api.service.SystemService;
import cn.codesensi.darius.api.vo.SystemVO;
import cn.codesensi.darius.common.annotation.ApiResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统信息 前端控制器
 *
 * @author codesensi
 * @since 2024-07-21 11:09:56
 */
@ApiResponseBody
@RestController
@Tag(name = "系统信息接口", description = "系统信息接口")
@RequestMapping("/system")
public class SystemController {

    @Resource
    private SystemService systemService;

    /**
     * 获取系统信息
     */
    @Operation(summary = "获取系统信息")
    @GetMapping("/info")
    public SystemVO info() {
        return systemService.info();
    }

}
