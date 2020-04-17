package com.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.pojo.Notice;
import org.springframework.stereotype.Component;

@Component
public interface NoticeDao extends BaseMapper<Notice> {
}
