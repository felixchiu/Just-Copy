package com.platformnexus.data.justCopy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@EnableAsync
@Configuration
public class AsyncConfig {

    @Value("${async.data.target.update:20}")
    private int TARGET_UPDATE_EXECUTOR_CORE_POOL_SIZE;

    @Value("${async.data.target.update:50}")
    private int TARGET_UPDATE_EXECUTOR_MAX_POOL_SIZE;


    @Bean(name = "target_update_executor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(TARGET_UPDATE_EXECUTOR_CORE_POOL_SIZE);
        executor.setCorePoolSize(TARGET_UPDATE_EXECUTOR_MAX_POOL_SIZE);
        return executor;
    }

}
