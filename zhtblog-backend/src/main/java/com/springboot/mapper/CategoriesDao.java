package com.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.pojo.Categories;
import org.springframework.stereotype.Component;

@Component
public interface CategoriesDao extends BaseMapper<Categories> {
}
