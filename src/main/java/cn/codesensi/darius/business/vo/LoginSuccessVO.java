package cn.codesensi.darius.business.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 登录成功后信息
 *
 * @author codesensi
 * @since 2024/1/21 15:39
 */
@Data
@Accessors(chain = true)
@Schema(name = "登录成功后信息", description = "登录成功后信息")
public class LoginSuccessVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 认证token
     */
    @Schema(description = "认证token")
    private String accessToken;

    /**
     * 刷新token
     */
    @Schema(description = "刷新token")
    private String refreshToken;
}
