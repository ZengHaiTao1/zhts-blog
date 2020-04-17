package com.springboot.RedisTemlateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public  class  CommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    private RedisService redisService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("项目启动了");
        System.out.println(redisService);
    }
}
