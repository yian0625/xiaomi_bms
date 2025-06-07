package com.example.xiaomibms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.example.xiaomibms.mapper")
@EnableScheduling
@EnableCaching
public class XiaomibmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaomibmsApplication.class, args);
    }

}
