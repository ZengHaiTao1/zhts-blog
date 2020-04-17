package com.springboot.controller.frontend;

import com.springboot.Service.NoticeService;
import com.springboot.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;


    @ResponseBody
    @RequestMapping(value = "/getBlogSummary",method = RequestMethod.GET)
    public JSONResult getBlogSummary(){
        Map<String, String> blogSummary = noticeService.getBlogSummary();
        return  JSONResult.successData(blogSummary);
    }
}
