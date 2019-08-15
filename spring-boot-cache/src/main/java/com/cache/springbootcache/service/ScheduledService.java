package com.cache.springbootcache.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
      //开启定时任务
   /* @Scheduled(cron = "0 * * * * MON-SAT")*/
    @Scheduled(cron = "0/4 * * * * ? ")
     public void ScheTest(){
         System.out.print("定时任务开启");
     }
}
