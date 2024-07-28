package cn.codesensi.darius.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 账号登录异常
 *
 * @author codesensi
 * @since 2024/1/10 22:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LoginException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public LoginException(String message) {
        super(message);
    }

}
