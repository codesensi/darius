package cn.codesensi.darius.common.task;

import cn.codesensi.darius.common.util.SpringUtil;
import cn.codesensi.darius.system.entity.SysOperateLog;
import cn.codesensi.darius.system.service.ISysOperateLogService;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;

/**
 * 任务工厂
 */
@Slf4j
public class TaskFactory {

    /**
     * 操作日志记录
     *
     * @param operateLog 操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOperateLog(final SysOperateLog operateLog) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtil.getBean(ISysOperateLogService.class).save(operateLog);
            }
        };
    }
}