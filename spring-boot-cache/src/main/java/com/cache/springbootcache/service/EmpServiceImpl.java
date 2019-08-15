package com.cache.springbootcache.service;

import com.cache.springbootcache.entity.Emp;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmpServiceImpl  implements EmpService{
    @Override
    @Cacheable(value = "Emp")
    public Emp getEmp( int id) {
        System.out.print("查询员工信息");
        return new Emp(12,"我大廖","123","打篮球");
    }
}
