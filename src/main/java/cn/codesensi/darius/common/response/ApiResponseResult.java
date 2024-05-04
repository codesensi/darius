package cn.codesensi.darius.common.response;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 接口统一响应对象
 *
 * @author codesensi
 * @since 2024/1/10 22:44
 */
@Data
public class ApiResponseResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 响应代码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private T data;

    public ApiResponseResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

}
