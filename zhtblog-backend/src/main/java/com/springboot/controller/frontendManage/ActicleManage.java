package com.springboot.controller.frontendManage;

import com.springboot.Service.ArticlsService;
import com.springboot.annotation.AuthToken;
import com.springboot.pojo.Articls;
import com.springboot.pojo.Draftsarticles;
import com.springboot.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ActicleManage {

    @Autowired
    private ArticlsService articlsService;

    @ResponseBody
    @RequestMapping(value = "/saveActicle/{draftsArticleId}",method = RequestMethod.POST)
    @AuthToken
    public JSONResult saveActicle(@RequestBody Articls articls,@PathVariable(value = "draftsArticleId") String draftsArticleId){
        boolean b = articlsService.saveActicle(articls,draftsArticleId);
        if(b){
            return  JSONResult.success();
        }else {
            return  JSONResult.failMsg("服务器异常");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/savedrAftsArticles",method = RequestMethod.POST)
    @AuthToken
    public JSONResult savedrAftsArticles(@RequestBody Draftsarticles draftsarticlesr){
        Integer integer = articlsService.savedrAftsArticles(draftsarticlesr);
            return  JSONResult.successData(integer);
    }

    @ResponseBody
    @RequestMapping(value = "/getArticlsByPage",method = RequestMethod.GET)
    public  JSONResult getArticlsByPage(@RequestParam Integer page){
        System.out.println(1);
        List<Articls> articlsByPage = articlsService.getArticlsByPage(page);
        return  JSONResult.successData(articlsByPage);
    }

    @ResponseBody
    @RequestMapping(value = "/getArticlsByDate",method = RequestMethod.GET)
//    @AuthToken
    public  JSONResult getArticlsByDate(){
        List<Map<Integer, Integer>> articlsByDate = articlsService.getArticlsByDate();
        return  JSONResult.successData(articlsByDate);
    }

    @ResponseBody
    @RequestMapping(value = "/getArticlNum",method = RequestMethod.GET)
    public  JSONResult getArticlNum(){
        Integer articlNum = articlsService.getArticlNum();
        return  JSONResult.successData(articlNum);
    }

    @ResponseBody
    @RequestMapping(value = "/setArticlDisplay",method = RequestMethod.POST)
    @AuthToken
    public  JSONResult setArticlDisplay(@RequestParam String id,@RequestParam String tf){
        boolean b = articlsService.setArticlDisplay(id, tf);
        if(b){
            return  JSONResult.success();
        }else {
            return  JSONResult.failed("修改失败");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/updateArticle",method = RequestMethod.POST)
    @AuthToken
    public  JSONResult updateArticle(@RequestBody Articls articls){
        boolean b = articlsService.updateArticle(articls);
        if(b){
            return  JSONResult.success();
        }else {
            return JSONResult.failMsg("失败");
        }
    }

}
