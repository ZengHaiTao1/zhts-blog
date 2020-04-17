package com.springboot.controller.frontendManage;

import com.springboot.Service.DraftsarticlesService;
import com.springboot.annotation.AuthToken;
import com.springboot.pojo.Draftsarticles;
import com.springboot.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DraftsarticlesManage {

    @Autowired
    private DraftsarticlesService draftsarticlesService;

    @ResponseBody
    @RequestMapping(value = "/getAllDraftsarticles",method = RequestMethod.GET)
    @AuthToken
    public JSONResult getAllDraftsarticles(){
        List allDraftsarticles = draftsarticlesService.getAllDraftsarticles();
        return JSONResult.successData(allDraftsarticles);
    }

    @ResponseBody
    @RequestMapping(value = "/getDraftsArticleById",method = RequestMethod.GET)
    @AuthToken
    public  JSONResult getDraftsArticleById(@RequestParam Integer id){
        Draftsarticles draftsArticleById = draftsarticlesService.getDraftsArticleById(id);
        return  JSONResult.successData(draftsArticleById);
    }

    @ResponseBody
    @RequestMapping(value = "/deleteDraftsarticlesById",method = RequestMethod.POST)
    @AuthToken
    public JSONResult deleteDraftsarticlesById(@RequestParam Integer id){
        boolean b = draftsarticlesService.deleteDraftsarticlesById(id);
        if(b){
            return JSONResult.success();
        }else {
            return JSONResult.failMsg("失败");
        }
    }

}
