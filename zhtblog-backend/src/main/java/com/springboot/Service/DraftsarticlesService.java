package com.springboot.Service;

import com.springboot.mapper.DraftsarticlesDao;
import com.springboot.pojo.Draftsarticles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DraftsarticlesService {
    @Autowired
    private DraftsarticlesDao draftsarticlesDao;

    public List getAllDraftsarticles(){
        List<Draftsarticles> draftsarticles = draftsarticlesDao.selectList(null);
        return  draftsarticles;
    }

    public  Draftsarticles getDraftsArticleById(Integer id){
        Draftsarticles draftsarticles = draftsarticlesDao.selectById(id);
        return  draftsarticles;
    }

    public boolean deleteDraftsarticlesById(Integer id){
        int i = draftsarticlesDao.deleteById(id);
        if(i<=0){
            return  false;
        }
        return true;
    }

}
