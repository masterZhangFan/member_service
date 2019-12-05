package cn.gaozheng.sales.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * AsyncTaskConfig
 *
 * @author Cheng Bo
 * @version 1.0
 */
@Configuration
@EnableAsync
public class AsyncTaskConfig {

    @Value("${task.async.config.core-pool-size}")
    private int corePoolSize;
    @Value("${task.async.config.max-pool-size}")
    private int maxPoolSize;
    @Value("${task.async.config.queue-capacity}")
    private int queueCapacity;
    @Value("${task.async.config.keep-alive-seconds}")
    private int keepAliveSeconds;

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setBeanName("taskExecutor");
        executor.initialize();
        return executor;
    }

}
