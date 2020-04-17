package com.springboot.controller.frontend;

import com.springboot.Service.VisitorService;
import com.springboot.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class VisitorController {
    @Autowired
    private VisitorService visitorService;

    @ResponseBody
    @RequestMapping(value = "/addVisitor",method = RequestMethod.POST)
    public JSONResult addVisitor(@RequestParam(value = "ip") String ip,@RequestParam(value = "place") String place,HttpSession session){
        boolean b = visitorService.addVisitor(ip,place,session);
        if(b){
            return JSONResult.success();
        }
        return  JSONResult.failMsg("请求失败");
    }

    @ResponseBody
    @RequestMapping(value = "/addVisitorLeave",method = RequestMethod.POST)
    public  JSONResult addVisitorLeave(HttpSession session){
        boolean b = visitorService.addVisitorLeave(session);
        String id = session.getId();
        System.out.println(id);
        System.out.println("离开");
        if(b){
            return JSONResult.success();
        }
        return  JSONResult.failMsg("请求失败");
    }
}
