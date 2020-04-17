package com.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.pojo.Label;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface LabelDao extends BaseMapper<Label> {
    List<Label> getLabels();

}
