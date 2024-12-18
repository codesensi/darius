package cn.codesensi.darius.api.controller;

import cn.codesensi.darius.api.dto.AccountUserDTO;
import cn.codesensi.darius.api.service.LoginService;
import cn.codesensi.darius.api.vo.LoginSuccessVO;
import cn.codesensi.darius.common.annotation.ApiResponseBody;
import cn.dev33.satoken.annotation.SaIgnore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录 前端控制器
 *
 * @author codesensi
 * @since 2024-07-21 11:09:56
 */
@ApiResponseBody
@RestController
@Tag(name = "登录接口", description = "登录接口")
@RequestMapping()
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 账号密码登录
     */
    @SaIgnore
    @Operation(summary = "账号密码登录")
    @PostMapping("/login/account")
    public LoginSuccessVO loginAccount(@Validated @RequestBody AccountUserDTO accountUserDTO) {
        return loginService.loginAccount(accountUserDTO);
    }

}
