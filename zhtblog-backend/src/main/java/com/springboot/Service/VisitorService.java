package com.springboot.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboot.mapper.VisitorDao;
import com.springboot.pojo.Visitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class VisitorService {

    @Autowired
    private VisitorDao visitorDao;

    public List<Visitor> getVisitorByPage(Integer id){
        QueryWrapper qw = new QueryWrapper();
        qw.orderByDesc("intotime");
        IPage<Visitor> page = new Page<>(id,10);
        IPage<Visitor> visitorIPage = visitorDao.selectPage(page, qw);
        List<Visitor> records = visitorIPage.getRecords();
        return  records;
    }

    public  boolean addVisitor(String ip , String place, HttpSession session){
        Visitor visitor = new Visitor();
        visitor.setIp(ip);
        visitor.setPlace(place);
        visitor.setIntotime(new Date());
        int insert = visitorDao.insert(visitor);
        if(insert<=0){
            return  false;
        }
        session.setAttribute("user",visitor);
        return  true;
    }

    public  boolean addVisitorLeave(HttpSession session){
        Visitor user = (Visitor) session.getAttribute("user");
        if(Objects.isNull(user)){
            return  false;
        }
        user.setLeavetime(new Date());
        int i = visitorDao.updateById(user);
        if(i<=0){
            return  false;
        }
        return  true;
    }

    public Integer getVisitorNum(){
        Integer integer = visitorDao.selectCount(null);
        return  integer;
    }
}

