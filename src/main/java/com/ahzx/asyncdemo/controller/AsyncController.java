package com.ahzx.asyncdemo.controller;

import com.ahzx.asyncdemo.service.AsyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author think
 * @version 1.0
 * @date 2021/9/6 9:26
 */
@RestController
public class AsyncController {
    private static final Logger logger = LoggerFactory.getLogger(AsyncController.class);
    final
    AsyncService asyncService;

    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/test")
    public void test(int num) throws ExecutionException, InterruptedException {
        for (int i = 0; i < num; i++) {
            logger.info(Thread.currentThread().getName() + ":AsyncService");
            CompletableFuture<String> str = asyncService.asyncSay(num);
            logger.info("future get:{}", str.get());
        }
    }
    @GetMapping("/hello")
    public void hello(int num){
        asyncService.asyncSayWhyThreadPool(num);
    }
}
