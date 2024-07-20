package cn.codesensi.darius.common.config;

import cn.codesensi.darius.common.constant.Constant;
import cn.codesensi.darius.common.properties.ThreadPoolProperties;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class ThreadPoolConfig {

    @Resource
    private ThreadPoolProperties threadPoolProperties;

    /**
     * 执行异步任务
     */
    @Bean(name = Constant.ASYNC_TASK_EXECUTOR_NAME)
    public ThreadPoolTaskExecutor asyncTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadPoolProperties.getCorePoolSize());
        executor.setMaxPoolSize(threadPoolProperties.getMaxPoolSize());
        executor.setQueueCapacity(threadPoolProperties.getQueueCapacity());
        executor.setKeepAliveSeconds(threadPoolProperties.getKeepAliveSeconds());
        executor.setAllowCoreThreadTimeOut(threadPoolProperties.getAllowCoreThreadTimeout());
        executor.setWaitForTasksToCompleteOnShutdown(threadPoolProperties.getWaitForTasksToCompleteOnShutdown());
        executor.setAwaitTerminationSeconds(threadPoolProperties.getAwaitTerminationSeconds());
        executor.setThreadNamePrefix("async-task-");
        try {
            // 反射加载拒绝策略类
            Class<?> clazz = Class.forName("java.util.concurrent.ThreadPoolExecutor$" + threadPoolProperties.getRejectedExecutionHandler());
            executor.setRejectedExecutionHandler((RejectedExecutionHandler) clazz.getDeclaredConstructor().newInstance());
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            // 默认使用CallerRunsPolicy策略：直接在execute方法的调用线程中运行被拒绝的任务
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        }
        executor.initialize();
        return executor;
    }

    /**
     * 执行定时任务
     */
    @Bean(name = Constant.SCHEDULED_TASK_EXECUTOR_NAME)
    protected ScheduledExecutorService scheduledTaskExecutor() {
        return new ScheduledThreadPoolExecutor(threadPoolProperties.getCorePoolSize(),
                new BasicThreadFactory.Builder().namingPattern("scheduled-task-%d").daemon(true).build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
