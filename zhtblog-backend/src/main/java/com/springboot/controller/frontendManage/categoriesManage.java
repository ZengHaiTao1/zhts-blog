package com.springboot.controller.frontendManage;

import com.springboot.Service.CategoriesService;
import com.springboot.annotation.AuthToken;
import com.springboot.pojo.Categories;
import com.springboot.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class categoriesManage {
    @Autowired
    private CategoriesService categoriesService;

    @ResponseBody
    @RequestMapping(value = "/getCategoriesByDisplay",method = RequestMethod.GET)
    public JSONResult getAllCategorie(@RequestParam(value = "display",required = false) String display){
        List<Categories> allCategories = categoriesService.getCategoriesByDisplay(display);
        return  JSONResult.successData(allCategories);
    }

    @ResponseBody
    @RequestMapping(value = "/updateCategoriesDisplay",method = RequestMethod.POST)
    @AuthToken
    public  JSONResult updateCategoriesDisplay(@RequestParam String id ,@RequestParam String tf){
        boolean b = categoriesService.updateCategoriesDisplay(id, tf);
        if(b){
            return  JSONResult.success();
        }else {
            return  JSONResult.failMsg("失败");
        }
    }
}
