package cn.codesensi.darius.common.task;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 * 应用退出时关闭后台线程
 */
@Slf4j
@Component
public class ShutdownManager {

    @PreDestroy
    public void destroy() {
        shutdownTaskManager();
    }

    /**
     * 停止任务
     */
    private void shutdownTaskManager() {
        TaskManager.me().shutdown();
    }
}
