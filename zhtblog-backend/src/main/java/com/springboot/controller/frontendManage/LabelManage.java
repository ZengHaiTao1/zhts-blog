package com.springboot.controller.frontendManage;

import com.springboot.Service.LableService;
import com.springboot.annotation.AuthToken;
import com.springboot.pojo.Label;
import com.springboot.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LabelManage {
    @Autowired
    private LableService lableService;

    @ResponseBody
    @RequestMapping(value = "/getLableByDisplay",method = RequestMethod.GET)
    public JSONResult getLableByDisplay(@RequestParam(value = "display",required = false) String display){
        List<Label> allLabel = lableService.getAllLabel(display);
       return JSONResult.successData(allLabel);
    }

    @ResponseBody
    @RequestMapping(value = "/updateTagDisplay",method = RequestMethod.POST)
    @AuthToken
    public JSONResult updateTagDisplay(@RequestParam String id,@RequestParam String tf){
        boolean b = lableService.updateTagDisplay(id, tf);
        if (b) {
            return JSONResult.success();
        }else {
            return  JSONResult.failMsg("失败");
        }
    }
}
