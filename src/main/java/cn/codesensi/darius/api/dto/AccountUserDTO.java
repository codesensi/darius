package cn.codesensi.darius.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 账号密码登录参数
 *
 * @author codesensi
 * @since 2024-07-21 11:09:56
 */
@Data
@Accessors(chain = true)
@Schema(name = "账号密码登录参数", description = "账号密码登录参数")
public class AccountUserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户账号
     */
    @NotBlank(message = "登录账号不能为空")
    @Schema(description = "登录账号")
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "登录密码不能为空")
    @Schema(description = "登录密码")
    private String password;

    /**
     * 验证码唯一标识
     */
    @Schema(description = "验证码唯一标识")
    private String captchaKey;

    /**
     * 用户密码
     */
    @Schema(description = "验证码")
    private String captcha;

}
