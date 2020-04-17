package com.springboot.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.springboot.RedisTemlateUtil.RedisService;
import com.springboot.mapper.LabelDao;
import com.springboot.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class LableService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
     private RedisService redisService;

    public List<Label> getAllLabel(String display){
        QueryWrapper qw = new QueryWrapper();
        if(!Objects.isNull(display)){
            qw.eq("display",display);
        }
        List<Label> labels = labelDao.selectList(qw);
        return  labels;
    }

    public boolean updateTagDisplay(String id,String tf){
        UpdateWrapper uw = new UpdateWrapper();
        uw.eq("id",id);
        uw.set("display",tf);
        int update = labelDao.update(new Label(), uw);
        if(update<=0){
            return  false;
        }
        return  true;
    }

    public Label getTagNameById(Integer id){
        Label label = labelDao.selectById(id);
        return  label;
    }
}
