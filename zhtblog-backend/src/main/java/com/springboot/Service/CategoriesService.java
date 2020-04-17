package com.springboot.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.springboot.mapper.CategoriesDao;
import com.springboot.pojo.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class CategoriesService {
    @Autowired
    private CategoriesDao categoriesDao;

    public List<Categories> getCategoriesByDisplay(String open){
        QueryWrapper qw = new QueryWrapper();
        if(!Objects.isNull(open)){
            qw.eq("display",open);
        }
        List<Categories> categories = categoriesDao.selectList(qw);
        return  categories;
    }

    public boolean updateCategoriesDisplay(String id,String tf){
        UpdateWrapper uw = new UpdateWrapper();
        uw.eq("id",id);
        uw.set("display",tf);
        int update = categoriesDao.update(new Categories(), uw);
        if(update<=0){
            return  false;
        }
        return  true;
    }


    public  Categories getCategoriesNameById(Integer id){
        Categories categories = categoriesDao.selectById(id);
        return  categories;
    }
}
