package cn.codesensi.darius.auth.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 验证码结果对象
 */
@Data
@Accessors(chain = true)
@Schema(name = "验证码结果对象", description = "验证码结果对象")
public class CaptchaVO {

    /**
     * 唯一标识
     */
    @Schema(description = "唯一标识")
    private String key;

    /**
     * 验证码
     */
    @Schema(description = "验证码")
    private String result;
}
