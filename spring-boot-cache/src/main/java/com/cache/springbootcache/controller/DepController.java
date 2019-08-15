package com.cache.springbootcache.controller;

import com.cache.springbootcache.entity.Dep;
import com.cache.springbootcache.service.DepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DepController {
    @Autowired
    DepService depService;

    @RequestMapping("/test")
    public Map<String,Object> getDep(@RequestParam int id){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("message",depService.getList(id));
        return map;
    }
    @RequestMapping("/test1")
    public Map<String,Object> getDep(Dep dep){
        Map<String,Object> map=new HashMap<String,Object>();
        System.out.print("-------------更新id"+dep.getId());

        depService.upDateMessage(dep);
        map.put("message","succesful");
        return map;
    }
}
