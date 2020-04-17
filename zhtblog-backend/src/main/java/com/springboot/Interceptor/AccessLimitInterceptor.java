package com.springboot.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.springboot.Utils.IpUtils;
import com.springboot.annotation.AccessLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Component
public class AccessLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            // Handler 是否为 HandlerMethod 实例
            if(handler instanceof HandlerMethod){
                // 强转
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                // 获取方法
                Method method = handlerMethod.getMethod();
                // 是否有AccessLimit注解
                if(!method.isAnnotationPresent(AccessLimit.class)){
                    return true;
                }
                // 获取注解内容信息
                AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
                if(accessLimit == null){
                    return true;
                }
                int times = accessLimit.times();//请求次数
                int second = accessLimit.second();//请求时间范围
                //根据 IP + API 限流
                String key = IpUtils.getIpAddr(request) + request.getRequestURI();
                //根据key获取已请求次数
                Integer maxTimes = redisTemplate.opsForValue().get(key);
                if(maxTimes == null){
                    //set时一定要加过期时间
                    redisTemplate.opsForValue().set(key, 1, second, TimeUnit.SECONDS);
                }else if(maxTimes < times){
                    redisTemplate.opsForValue().set(key, maxTimes+1, second, TimeUnit.SECONDS);
                }else{
                    JSONObject jsonObject = new JSONObject();
                    PrintWriter out = null;
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                    jsonObject.put("code", ((HttpServletResponse) response).getStatus());
                    jsonObject.put("message", HttpStatus.UNAUTHORIZED);
                    out = response.getWriter();
                    out.println(jsonObject);
                    out.close();
                    return false;
                }
            }
        }catch (Exception e){

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
