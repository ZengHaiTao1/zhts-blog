package com.springboot.config;


import com.springboot.Interceptor.AccessLimitInterceptor;
import com.springboot.Interceptor.AuthorizationInterceptor;
import com.springboot.Interceptor.IPInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;

    @Autowired
    private AccessLimitInterceptor accessLimitInterceptor;

    @Autowired
    private IPInterceptor ipInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
        registry.addInterceptor(accessLimitInterceptor);
//        registry.addInterceptor(ipInterceptor);
    }
}
