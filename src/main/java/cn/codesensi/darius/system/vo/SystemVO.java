package cn.codesensi.darius.system.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 系统信息
 */
@Accessors(chain = true)
@Data
public class SystemVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private OsVO os;

    private CpuVO cpu;

    private MemoryVO memory;

    private SwapVO swap;

    private DiskVO disk;

    private Long time;
}
