package cn.codesensi.darius.api.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 通用信息
 */
@Data
public class BaseVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 总大小
     */
    private String total;

    /**
     * 空闲
     */
    private String available;

    /**
     * 已使用
     */
    private String used;

    /**
     * 使用率
     */
    private String usageRate;
}
