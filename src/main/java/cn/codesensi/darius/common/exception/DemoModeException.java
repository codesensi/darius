package cn.codesensi.darius.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 演示模式异常
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class DemoModeException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DemoModeException(String message) {
        super(message);
    }
}
