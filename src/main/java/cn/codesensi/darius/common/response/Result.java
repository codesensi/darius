package cn.codesensi.darius.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 接口统一响应参数
 *
 * @author codesensi
 * @since 2024/1/10 22:44
 */
@Data
@Accessors(chain = true)
@Schema(name = "接口统一响应参数", description = "接口统一响应参数")
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 响应代码
     */
    @Schema(description = "响应代码")
    private Integer code;

    /**
     * 响应消息
     */
    @Schema(description = "响应消息")
    private String message;

    /**
     * 响应结果
     */
    @Schema(description = "响应结果")
    private T data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
