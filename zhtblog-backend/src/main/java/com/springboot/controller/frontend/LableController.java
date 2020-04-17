package com.springboot.controller.frontend;

import com.springboot.Service.LableService;
import com.springboot.pojo.Label;
import com.springboot.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LableController {

    @Autowired
    private LableService lableService;

    @ResponseBody
    @RequestMapping(value = "/getTagNameById",method = RequestMethod.GET)
    public JSONResult getTagNameById(@RequestParam Integer id){
        Label tagNameById = lableService.getTagNameById(id);
        return  JSONResult.successData(tagNameById);
    }

}
