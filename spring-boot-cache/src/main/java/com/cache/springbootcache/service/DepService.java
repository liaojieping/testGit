package com.cache.springbootcache.service;

import com.cache.springbootcache.entity.Dep;

import java.util.List;

public interface DepService {
    public List<Dep> getList(int id);
    public List<Dep> upDateMessage(Dep dep);
}
