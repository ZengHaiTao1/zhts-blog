package com.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.pojo.Articls;
import org.springframework.stereotype.Component;

@Component
//@CacheNamespace(implementation= cacle.class,eviction=cacle.class)
public interface ArticlsDao extends BaseMapper<Articls> {

}
