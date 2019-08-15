package com.cache.springbootcache.controller;

import com.cache.springbootcache.entity.Emp;
import com.cache.springbootcache.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmpController {
    @Autowired
    EmpService empService;
    @RequestMapping("/testEmp")
    public Emp getE(@RequestParam int id){
        return empService.getEmp(id);
    }
}
