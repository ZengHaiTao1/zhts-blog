package com.springboot.controller.frontend;

import com.springboot.Service.CategoriesService;
import com.springboot.pojo.Categories;
import com.springboot.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CategoriesController {
    @Autowired
    private CategoriesService categoriesService;

    @ResponseBody
    @RequestMapping(value = "/getCategoriesNameById",method = RequestMethod.GET)
    public JSONResult getCategoriesNameById(@RequestParam Integer id){
        Categories categoriesNameById = categoriesService.getCategoriesNameById(id);
        return  JSONResult.successData(categoriesNameById);
    }
}
