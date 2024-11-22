package cn.codesensi.darius.api.service.impl;

import cn.codesensi.darius.api.service.SystemService;
import cn.codesensi.darius.api.task.TaskUtil;
import cn.codesensi.darius.api.vo.*;
import cn.codesensi.darius.common.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.VirtualMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 系统信息接口实现
 */
@Slf4j
@Service
public class SystemServiceImpl implements SystemService {

    private final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * 获取系统信息
     */
    @Override
    public SystemVO info() {
        SystemVO systemVO = new SystemVO();
        SystemInfo si = new SystemInfo();
        OperatingSystem os = si.getOperatingSystem();
        HardwareAbstractionLayer hal = si.getHardware();
        CpuVO cpuInfo = this.getCpuInfo(hal.getProcessor());
        MemoryVO memoryInfo = this.getMemoryInfo(hal.getMemory());
        SwapVO swapInfo = this.getSwapInfo(hal.getMemory());
        DiskVO diskInfo = this.getDiskInfo(os);
        OsVO osInfo = this.getOsInfo(os);

        return systemVO.setCpu(cpuInfo)
                .setMemory(memoryInfo)
                .setDisk(diskInfo)
                .setSwap(swapInfo)
                .setOs(osInfo);
    }

    /**
     * 获取CPU信息
     */
    private CpuVO getCpuInfo(CentralProcessor processor) {
        CpuVO cpuInfo = new CpuVO();
        // 基本信息
        cpuInfo.setName(processor.getProcessorIdentifier().getName());
        cpuInfo.setNumber(processor.getPhysicalPackageCount());
        cpuInfo.setCore(processor.getPhysicalProcessorCount());
        cpuInfo.setLogic(processor.getLogicalProcessorCount());
        // 占用信息
        long[] startTicks = processor.getSystemCpuLoadTicks();
        TaskUtil.sleep(1000);
        long[] endTicks = processor.getSystemCpuLoadTicks();
        long user = endTicks[CentralProcessor.TickType.USER.getIndex()] - startTicks[CentralProcessor.TickType.USER.getIndex()];
        long nice = endTicks[CentralProcessor.TickType.NICE.getIndex()] - startTicks[CentralProcessor.TickType.NICE.getIndex()];
        long sys = endTicks[CentralProcessor.TickType.SYSTEM.getIndex()] - startTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long idle = endTicks[CentralProcessor.TickType.IDLE.getIndex()] - startTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long ioWait = endTicks[CentralProcessor.TickType.IOWAIT.getIndex()] - startTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long irq = endTicks[CentralProcessor.TickType.IRQ.getIndex()] - startTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softIrq = endTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - startTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = endTicks[CentralProcessor.TickType.STEAL.getIndex()] - startTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long totalCpu = user + nice + sys + idle + ioWait + irq + softIrq + steal;
        if (totalCpu <= 0) {
            cpuInfo.setUsed("0");
            cpuInfo.setIdle("0");
        } else {
            cpuInfo.setUsed(df.format(100d * user / totalCpu + 100d * sys / totalCpu));
            cpuInfo.setIdle(df.format(100d * idle / totalCpu));
        }
        return cpuInfo;
    }

    /**
     * 获取内存信息
     */
    private MemoryVO getMemoryInfo(GlobalMemory memory) {
        MemoryVO memoryInfo = new MemoryVO();
        long total = memory.getTotal();
        long available = memory.getAvailable();
        long used = total - available;
        extracted(total, used, memoryInfo);
        return memoryInfo;
    }

    /**
     * 获取转换空间信息
     */
    private SwapVO getSwapInfo(GlobalMemory memory) {
        SwapVO swapInfo = new SwapVO();
        VirtualMemory virtualMemory = memory.getVirtualMemory();
        long total = virtualMemory.getSwapTotal();
        long used = virtualMemory.getSwapUsed();
        extracted(total, used, swapInfo);
        return swapInfo;
    }

    /**
     * 获取硬盘信息
     */
    private DiskVO getDiskInfo(OperatingSystem os) {
        DiskVO diskInfo = new DiskVO();
        FileSystem fileSystem = os.getFileSystem();
        List<OSFileStore> fsArray = fileSystem.getFileStores();
        String osName = System.getProperty("os.name");
        long available = 0, total = 0;
        if (osName.toLowerCase().startsWith("win")) {
            for (OSFileStore fs : fsArray) {
                available += fs.getUsableSpace();
                total += fs.getTotalSpace();
            }
        } else {
            Set<String> names = new HashSet<>(fsArray.size());
            for (OSFileStore fs : fsArray) {
                if (names.add(fs.getName())) {
                    available = fs.getUsableSpace();
                    total = fs.getTotalSpace();
                }
            }
        }
        long used = total - available;
        extracted(total, used, diskInfo);
        return diskInfo;
    }

    /**
     * 获取系统OS信息
     */
    private OsVO getOsInfo(OperatingSystem os) {
        OsVO osInfo = new OsVO();
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        osInfo.setName(os.toString());
        osInfo.setIp(IpUtil.getIpAddr());
        osInfo.setRuntime(System.currentTimeMillis() - time);
        return osInfo;
    }

    /**
     * 获取通用信息
     *
     * @param total
     * @param used
     * @param baseVO
     */
    private void extracted(long total, long used, BaseVO baseVO) {
        long available = total - used;
        String usageRate = "0";
        if (total > 0 && used > 0) {
            usageRate = df.format(used * 100d / total);
        }
        baseVO.setTotal(FormatUtil.formatBytes(total));
        baseVO.setAvailable(FormatUtil.formatBytes(available));
        baseVO.setUsed(FormatUtil.formatBytes(used));
        baseVO.setUsageRate(usageRate);
    }
}
