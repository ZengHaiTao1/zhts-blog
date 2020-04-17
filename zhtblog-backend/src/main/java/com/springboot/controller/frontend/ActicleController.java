package com.springboot.controller.frontend;

import com.springboot.Service.ArticlsService;
import com.springboot.annotation.AccessLimit;
import com.springboot.pojo.Articls;
import com.springboot.result.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
//@CrossOrigin(origins = "http://www.baidu.com")
@CrossOrigin
public class ActicleController {

    @Autowired
    private ArticlsService articlsService;

    private static final Logger logger = LoggerFactory.getLogger(ActicleController.class);

    @ResponseBody
    @RequestMapping(value = "/getArticlsByReading",method = RequestMethod.GET)
    public JSONResult getArticlsByReading(@RequestParam(value = "reading",required = false) String reading){
        List articlsByReading = articlsService.getArticlsByReading(reading);
        return  JSONResult.successData(articlsByReading);
    }

    @ResponseBody
    @RequestMapping(value = "/getTimeAxis",method = RequestMethod.GET)
    public  JSONResult getTimeAxis(@RequestParam(value = "categorie",required = false) String categorie,@RequestParam(value="tag",required = false) String tag ){
        List timeAxis = articlsService.getTimeAxis(categorie,tag);
        return  JSONResult.successData(timeAxis);
    }


    @ResponseBody
    @AccessLimit
    @RequestMapping(value = "/getArticlsById",method = RequestMethod.GET)
    public  JSONResult getArticlsById(@RequestParam(value = "id")Integer id){
        Articls articlsById = articlsService.getArticlsById(id);
        return  JSONResult.successData(articlsById);
    }

    @ResponseBody
    @RequestMapping(value = "/addBrowse",method = RequestMethod.GET)
    public  JSONResult addBrowse(@RequestParam(value = "id") Integer id){
        Boolean aBoolean = articlsService.addBrowse(id);
        return  JSONResult.successData(aBoolean);
    }

    @ResponseBody
    @RequestMapping(value = "/addLikearticls",method = RequestMethod.GET)
    public  JSONResult addLikearticls(@RequestParam(value = "id") Integer id){
        Boolean aBoolean = articlsService.addLikearticls(id);
        return  JSONResult.successData(aBoolean);
    }

    @ResponseBody
    @RequestMapping(value = "/getBrowseAndLike",method = RequestMethod.GET)
    public  JSONResult getBrowseAndLike(@RequestParam Integer id){
        Map browseAndLike = articlsService.getBrowseAndLike(id);
        return  JSONResult.successData(browseAndLike);
    }

}
