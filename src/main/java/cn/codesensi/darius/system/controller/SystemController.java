package cn.codesensi.darius.system.controller;

import cn.codesensi.darius.common.annotation.ApiResponseBody;
import cn.codesensi.darius.common.util.IpUtil;
import cn.codesensi.darius.system.task.TaskUtil;
import cn.codesensi.darius.system.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
 * 系统信息 前端控制器
 *
 * @author codesensi
 * @since 2024-07-21 11:09:56
 */
@ApiResponseBody
@RestController
@Tag(name = "系统信息接口", description = "系统信息接口")
@RequestMapping("/system")
public class SystemController {

    private final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * 账号密码登录
     */
    @Operation(summary = "获取系统信息")
    @GetMapping("/info")
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

    private MemoryVO getMemoryInfo(GlobalMemory memory) {
        MemoryVO memoryInfo = new MemoryVO();
        long total = memory.getTotal();
        long available = memory.getAvailable();
        long used = total - available;

        memoryInfo.setTotal(FormatUtil.formatBytes(total));
        memoryInfo.setAvailable(FormatUtil.formatBytes(available));
        memoryInfo.setUsed(FormatUtil.formatBytes(used));
        String usageRate = "0";
        if (total > 0 && used > 0) {
            usageRate = df.format(used * 100d / total);
        }
        memoryInfo.setUsageRate(usageRate);
        return memoryInfo;
    }

    private SwapVO getSwapInfo(GlobalMemory memory) {
        SwapVO swapInfo = new SwapVO();
        VirtualMemory virtualMemory = memory.getVirtualMemory();
        long total = virtualMemory.getSwapTotal();
        long used = virtualMemory.getSwapUsed();
        long available = total - used;

        swapInfo.setTotal(FormatUtil.formatBytes(total));
        swapInfo.setAvailable(FormatUtil.formatBytes(available));
        swapInfo.setUsed(FormatUtil.formatBytes(used));
        String usageRate = "0";
        if (total > 0 && used > 0) {
            usageRate = df.format(used * 100d / total);
        }
        swapInfo.setUsageRate(usageRate);
        return swapInfo;
    }

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

        diskInfo.setTotal(FormatUtil.formatBytes(total));
        diskInfo.setAvailable(FormatUtil.formatBytes(available));
        diskInfo.setUsed(FormatUtil.formatBytes(used));
        String usageRate = "0";
        if (total > 0 && used > 0) {
            usageRate = df.format(used * 100d / total);
        }
        diskInfo.setUsageRate(usageRate);

        return diskInfo;
    }

    private OsVO getOsInfo(OperatingSystem os) {
        OsVO osInfo = new OsVO();
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        osInfo.setName(os.toString());
        osInfo.setIp(IpUtil.getIpAddr());
        osInfo.setRuntime(System.currentTimeMillis() - time);
        return osInfo;
    }

}
