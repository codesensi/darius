package cn.codesensi.darius.api.task;

import cn.codesensi.darius.common.util.SpringUtil;
import cn.codesensi.darius.sys.entity.SysLoginLog;
import cn.codesensi.darius.sys.entity.SysOperateLog;
import cn.codesensi.darius.sys.service.ISysLoginLogService;
import cn.codesensi.darius.sys.service.ISysOperateLogService;
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

    /**
     * 登录登出记录
     *
     * @param loginLog 登录登出日志
     * @return 任务task
     */
    public static TimerTask recordAuthLog(final SysLoginLog loginLog) {
        return new TimerTask() {
            @Override
            public void run() {
                SpringUtil.getBean(ISysLoginLogService.class).save(loginLog);
            }
        };
    }
}
