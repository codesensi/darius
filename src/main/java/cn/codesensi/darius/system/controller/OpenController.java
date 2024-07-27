package cn.codesensi.darius.system.controller;

import cn.codesensi.darius.common.annotation.ApiResponseBody;
import cn.codesensi.darius.common.strategy.CaptchaStrategyContext;
import cn.codesensi.darius.system.dto.AccountUserDTO;
import cn.codesensi.darius.system.dto.CaptchaDTO;
import cn.codesensi.darius.system.service.LoginService;
import cn.codesensi.darius.system.vo.CaptchaVO;
import cn.codesensi.darius.system.vo.LoginSuccessVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 开放接口 前端控制器
 *
 * @author codesensi
 * @since 2024-07-21 11:09:56
 */
@ApiResponseBody
@RestController
@Tag(name = "开放接口", description = "开放接口")
@RequestMapping()
public class OpenController {

    @Resource
    private LoginService loginService;
    @Resource
    private CaptchaStrategyContext captchaStrategyContext;

    /**
     * 账号密码登录
     */
    @Operation(summary = "账号密码登录")
    @PostMapping("/login/account")
    public LoginSuccessVO loginAccount(@Validated @RequestBody AccountUserDTO accountUserDTO) {
        return loginService.loginAccount(accountUserDTO);
    }

    /**
     * 获取验证码
     */
    @Operation(summary = "获取验证码")
    @GetMapping("/captcha")
    public CaptchaVO captcha(@Validated @ParameterObject CaptchaDTO captchaDTO) {
        return captchaStrategyContext.captcha(captchaDTO);
    }
}
