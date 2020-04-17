package com.springboot.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.springboot.Utils.IpUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class IPInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        ArrayList<String> ipArr = new ArrayList<String>();
        ipArr.add("49.234.222.203");
        ipArr.add("172.17.0.17");
        ipArr.add("127.0.0.1");
        //过滤ip,若用户在白名单内，则放行
        String ipAddress= IpUtils.getIpAddr(request);
        System.out.println(ipAddress);
        for(Integer i = 0 ; i < ipArr.size() ; i++){
            if(Objects.equals(ipArr.get(i),ipAddress)){
                return  true;
            }
        }
        JSONObject jsonObject = new JSONObject();
        PrintWriter out = null;
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        jsonObject.put("code", ((HttpServletResponse) response).getStatus());
        jsonObject.put("message", "需要接口测试请联系博主。");
        out = response.getWriter();
        out.println(jsonObject);
        out.close();
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}