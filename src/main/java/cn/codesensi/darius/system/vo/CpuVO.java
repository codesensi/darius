package cn.codesensi.darius.system.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * CPU信息
 */
@Data
public class CpuVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * CPU名称
     */
    private String name;

    /**
     * 物理CPU
     */
    private Integer number;

    /**
     * 逻辑CPU
     */
    private Integer logic;

    /**
     * 物理核心数
     */
    private Integer core;

    /**
     * 空闲
     */
    private String idle;

    /**
     * 使用
     */
    private String used;
}
