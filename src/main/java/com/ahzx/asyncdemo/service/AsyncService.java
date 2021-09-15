package com.ahzx.asyncdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @author think
 * @version 1.0
 * @date 2021/9/6 9:26
 */
@Service
public class AsyncService {
    private static final Logger logger = LoggerFactory.getLogger(AsyncService.class);

    /**
     * Method annotated with @Async should return ''void'' or "Future-like" type
     */
    @Async
    public CompletableFuture<String> asyncSay(int num) {
        logger.info(Thread.currentThread().getName() + ":你看看我是不是异步的呢?");
        return CompletableFuture.completedFuture("hi:" + num);
    }

    @Async("whyThreadPool")
    public void asyncSayWhyThreadPool(int num) {
        logger.info(Thread.currentThread().getName()+":asyncSayWhyThreadPool.num:"+num);
    }

    @Bean("whyThreadPool")
    public Executor whyThreadPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池的大小
        executor.setCorePoolSize(8);
        //最大线程数
        executor.setMaxPoolSize(12);
        //队列容量
        executor.setQueueCapacity(100);
        //活跃时间
        executor.setKeepAliveSeconds(60);
        //线程明治前缀
        executor.setThreadNamePrefix("whyThreadPool-");
        executor.initialize();
        return executor;
    }


}
