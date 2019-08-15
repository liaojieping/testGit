package com.cache.springbootcache.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/springSecurity")
public class springSecurityContrller {
    @RequestMapping("/hello")
    public String test(){
        return "hello";
    }
}
