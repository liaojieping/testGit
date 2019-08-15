package com.cache.springbootcache.service;

import com.cache.springbootcache.entity.Dep;
import com.cache.springbootcache.mapper.DepMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;                //此次缓存将数据保存在concruuentMapCacheMager管理器
@CacheConfig(cacheNames = "Dep")        //此注解表示缓存配置，抽取缓存公共配置，在下面的注解中，value="Dep"，可以不用写了（此类缓存组件名称为Dep）
@Service
public class DepServiceImp implements DepService{
    @Autowired
    DepMapper depMapper;
    @Override                                                          //key的默认生成策略为id，及注解上不指定key
    /*@Cacheable(cacheNames = "Dep",key = "#root.methodName+'['+#id+']'")*///将查询接结果加到缓存中(缓存)，cache组件的名为Dep,缓存提供者-缓冲管理-缓冲组件
    @Cacheable(cacheNames = "Dep"/*,keyGenerator = "myKeyGenerator",condition = "#id>1",unless = "#id==2"*/)   //自定义key的生成策略，没有进行相应的测试，待测试,condition代表数据条件判断进行缓存
    public List<Dep> getList(int id) {                    //unless代表在什么条件下不缓存，如例子中，在id=2时不缓存
        System.out.print("第一次运行"+"------------------");
        return depMapper.getList(id);
    }

    @Override
   @CachePut(cacheNames ="Dep",key="#dep.id")            //先执行方法，后把结果存到缓存中,更新缓存*/
    public  List<Dep> upDateMessage(Dep dep) {
        System.out.print("更新运行"+"------------------");
        depMapper.upDateMessage(dep);
        return getList(dep.getId());
    }
    @CacheEvict(value = "Dep",key = "#id",allEntries = true )   //删除缓存,allEntries代表删除所有的缓存，此时Key就没有用了，key时指删除具体哪个，allEntries默认false
    //清除缓存默认是在方法之后执行,beforeInvocation=true代表在方法执行之前清除
    public void deleteCahce(int id){
          System.out.print("删除缓存");
    }


    @Caching(cacheable = {
            @Cacheable(value="Dep",key = "#name")          //适用于复杂组合
    },
             put = {
            @CachePut(value = "Dep",key = "#name"),
            @CachePut(value = "Dep",key = "#result.id")
             })
    public Dep test(String name){  //这是关于根据名称查询的方法，暂时没有实现，为了方便理解Cachingz注解
         return new Dep();
    }
}
