package cn.codesensi.darius.auth.controller;

import cn.codesensi.darius.auth.dto.AccountUserDTO;
import cn.codesensi.darius.auth.dto.CaptchaDTO;
import cn.codesensi.darius.auth.service.AuthService;
import cn.codesensi.darius.auth.strategy.CaptchaStrategyContext;
import cn.codesensi.darius.auth.vo.CaptchaVO;
import cn.codesensi.darius.auth.vo.LoginSuccessVO;
import cn.codesensi.darius.common.annotation.ApiResponseBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 账号鉴权 前端控制器
 *
 * @author codesensi
 * @since 2024-07-21 11:09:56
 */
@ApiResponseBody
@RestController
@Tag(name = "账号鉴权接口", description = "账号鉴权接口")
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private AuthService authService;
    @Resource
    private CaptchaStrategyContext captchaStrategyContext;

    /**
     * 账号密码登录
     */
    @Operation(summary = "账号密码登录")
    @PostMapping("/login/account")
    public LoginSuccessVO account(@Validated @RequestBody AccountUserDTO accountUserDTO) {
        return authService.account(accountUserDTO);
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
