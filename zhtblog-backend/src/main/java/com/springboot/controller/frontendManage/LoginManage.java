package com.springboot.controller.frontendManage;

import com.springboot.RedisTemlateUtil.RedisService;
import com.springboot.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;
import java.util.UUID;

@Controller
public class LoginManage {

    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(value = "/Login",method = RequestMethod.POST)
    public JSONResult Login(@RequestParam String username , @RequestParam String password,@RequestParam(name = "token",required = false) String token){

        if(Objects.equals(username,"13135693408")&&Objects.equals(password,"...zht000000")){
            UUID uuid = UUID.randomUUID();
            String mytoken = uuid.toString();
//            session.setAttribute("token",token);
            redisService.set("token",mytoken,1);
//            session.setMaxInactiveInterval(60*60*5);
            return  JSONResult.successData(mytoken);
        }
        return  JSONResult.failMsg("账号或者密码错误！");
    }


    @ResponseBody
    @RequestMapping(value = "/LoginOut",method = RequestMethod.POST)
    public  JSONResult LoginOut(@RequestParam String token){
        String token1 = (String) redisService.get("token");
        if(Objects.isNull(token1)){
            return  JSONResult.success();
        }else if(Objects.equals(token1,token)) {
            redisService.remove("token");
            return  JSONResult.success();
        }
        return  JSONResult.failMsg("token无效");
    }

    @ResponseBody
    @RequestMapping(value = "/token",method = RequestMethod.POST)
    public  JSONResult token(@RequestParam String token){
        String token1 = (String) redisService.get("token");
        if(Objects.isNull(token1)){
            return  JSONResult.failMsg("登陆信息失效");
        }else if(Objects.equals(token1,token)) {
            return  JSONResult.success();
        }
        return  JSONResult.failMsg("账号已经在别处登陆无效");
    }
}
