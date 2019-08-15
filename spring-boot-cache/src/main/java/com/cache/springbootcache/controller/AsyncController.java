package com.cache.springbootcache.controller;

import com.cache.springbootcache.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                       //异步任务测试
public class AsyncController {
    @Autowired
    AsyncService asyncService;
    @RequestMapping("/HAHA")
    public String HEllo(){
        asyncService.sleepTest();
        return "successful";
    }
}
