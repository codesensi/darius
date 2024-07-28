package cn.codesensi.darius.system.controller;

import cn.codesensi.darius.common.annotation.ApiResponseBody;
import cn.codesensi.darius.system.dto.CaptchaDTO;
import cn.codesensi.darius.system.strategy.captcha.CaptchaStrategyContext;
import cn.codesensi.darius.system.vo.CaptchaVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码 前端控制器
 *
 * @author codesensi
 * @since 2024-07-21 11:09:56
 */
@ApiResponseBody
@RestController
@Tag(name = "验证码接口", description = "验证码接口")
@RequestMapping()
public class CaptchaController {

    @Resource
    private CaptchaStrategyContext captchaStrategyContext;

    /**
     * 生成验证码
     */
    @Operation(summary = "生成验证码")
    @GetMapping("/captcha")
    public CaptchaVO captcha(@Validated @ParameterObject CaptchaDTO captchaDTO) {
        return captchaStrategyContext.captcha(captchaDTO);
    }
}
