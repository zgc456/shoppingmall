package com.zhkj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication//springbootapplication应用注解
@EnableConfigServer//configserver注解
@EnableDiscoveryClient//发现服务注解
public class Shopping_Mall_Config_Application {
    public static void main(String[] args) {
        SpringApplication.run(Shopping_Mall_Config_Application.class,args);
    }
}
