package com.springboot.Service;

import com.springboot.mapper.ArticlsDao;
import com.springboot.mapper.CategoriesDao;
import com.springboot.mapper.LabelDao;
import com.springboot.mapper.NoticeDao;
import com.springboot.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class NoticeService {

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private ArticlsDao articlsDao;

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private CategoriesDao categoriesDao;

    public Notice getNotice(){
        Notice notice = noticeDao.selectById(1);
        return  notice;
    }

    public boolean setNotice(String str){
        Notice notice = new Notice();
        notice.setId(1);
        notice.setNotice(str);
        int i = noticeDao.updateById(notice);
        if(i<=0){
            return  false;
        }
        return  true;
    }

    /**
     * 获取博客公告和文章数，类别数，标签数
     * @return
     */
    public Map<String,String> getBlogSummary(){
        Notice notice = getNotice();
        String postnum = String.valueOf(articlsDao.selectCount(null));
        String tagnum = String.valueOf(labelDao.selectCount(null));
        String categoriesnum = String.valueOf(categoriesDao.selectCount(null));
        Map<String,String> data = new HashMap<>();
        data.put("description",notice.getNotice());
        data.put("postnum",postnum);
        data.put("tagnum",tagnum);
        data.put("categoriesnum",categoriesnum);
        return  data;
    }
}
