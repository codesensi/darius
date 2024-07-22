package cn.codesensi.darius.business.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 验证码对象
 */
@Data
@Accessors(chain = true)
@Schema(name = "验证码对象", description = "验证码对象")
public class CaptchaDTO {

    /**
     * 唯一标识
     */
    @NotBlank(message = "验证码类型不能为空")
    @Schema(description = "验证码类型")
    private String type;

    /**
     * 手机号 短信验证码登陆时必填
     */
    @Schema(description = "手机号")
    private String phone;
}
