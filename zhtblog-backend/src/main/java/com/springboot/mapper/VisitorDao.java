package com.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.pojo.Visitor;
import org.springframework.stereotype.Component;

@Component
public interface VisitorDao  extends BaseMapper<Visitor> {
}
