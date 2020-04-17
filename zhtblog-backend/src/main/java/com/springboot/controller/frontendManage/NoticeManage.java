package com.springboot.controller.frontendManage;

import com.springboot.Service.NoticeService;
import com.springboot.annotation.AuthToken;
import com.springboot.pojo.Notice;
import com.springboot.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NoticeManage {

    @Autowired
    private NoticeService noticeService;

    @ResponseBody
    @RequestMapping(value = "/getNotice",method = RequestMethod.GET)
    public JSONResult getNotice(){
        Notice notice = noticeService.getNotice();
        return  JSONResult.successData(notice);
    }

    @ResponseBody
    @RequestMapping(value = "/updateNotice",method = RequestMethod.POST)
    @AuthToken
    public JSONResult updateNotice(@RequestParam("notice") String notice){
        boolean b = noticeService.setNotice(notice);
        return  JSONResult.successData(notice);
    }

}
