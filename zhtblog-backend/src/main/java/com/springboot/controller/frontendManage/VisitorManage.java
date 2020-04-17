package com.springboot.controller.frontendManage;

import com.springboot.Service.VisitorService;
import com.springboot.annotation.AuthToken;
import com.springboot.pojo.Visitor;
import com.springboot.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class VisitorManage {

    @Autowired
    private VisitorService visitorService;

    @ResponseBody
    @RequestMapping(value = "/getVisitorByPage",method = RequestMethod.GET)
    @AuthToken
    public JSONResult getVisitorByPage(@RequestParam(value = "id") Integer id){
        List<Visitor> visitorByPage = visitorService.getVisitorByPage(id);
        return  JSONResult.successData(visitorByPage);
    }

    @ResponseBody
    @RequestMapping(value = "/getVisitorNum",method = RequestMethod.GET)
    @AuthToken
    public  JSONResult getVisitorNum(){
        Integer visitorNum = visitorService.getVisitorNum();
        return  JSONResult.successData(visitorNum);
    }
}
