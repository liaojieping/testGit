package com.cache.springbootcache.mapper;

import com.cache.springbootcache.entity.Dep;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
@Component(value = "DepMapper")
public interface DepMapper {
     public List<Dep> getList(@Param("id") int id);
     public int upDateMessage(Dep dep);
}
