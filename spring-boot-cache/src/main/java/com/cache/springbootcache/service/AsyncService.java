package com.cache.springbootcache.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
      //异步任务，指线程高并发时同时处理多个任务（方法）
    @Async
    public void sleepTest() {
        try {
            Thread.sleep(3000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.print("开始看看咯");
    }
}
