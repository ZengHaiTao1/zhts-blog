package com.springboot.Interceptor;

import com.alibaba.fastjson.JSONObject;
import com.springboot.RedisTemlateUtil.RedisService;
import com.springboot.annotation.AuthToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Objects;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {

    private String httpHeaderName = "Authorization";

    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    //鉴权失败后返回的错误信息，默认为401 unauthorized
    private String unauthorizedErrorMessage = "401 unauthorized";

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 如果打上了AuthToken注解则需要验证token
        if (method.getAnnotation(AuthToken.class) != null || handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {
            String token = request.getHeader("token");
            System.out.println(token);
            String redistoken = (String) redisService.get("token");
//            HttpSession session = request.getSession();
//            String  sessiontoken = String.valueOf(session.getAttribute("token"));
            JSONObject jsonObject = new JSONObject();
            PrintWriter out = null;
            if(Objects.equals(token,redistoken)){
                return  true;
            }else{
                response.setStatus(unauthorizedErrorCode);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                jsonObject.put("code", ((HttpServletResponse) response).getStatus());
                jsonObject.put("message", HttpStatus.UNAUTHORIZED);
                out = response.getWriter();
                out.println(jsonObject);
                out.close();
                return  false;
            }

        }
            return  true;
    }
    }
