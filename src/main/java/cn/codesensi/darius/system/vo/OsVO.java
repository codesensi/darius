package cn.codesensi.darius.system.vo;

import lombok.Data;

/**
 * OS信息
 */
@Data
public class OsVO {

    /**
     * 名称
     */
    private String name;

    /**
     * IP
     */
    private String ip;

    /**
     * 运行时长
     */
    private Long runtime;
}
