package com.zhkj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心三实现集群
 */
@SpringBootApplication
@EnableEurekaServer
public class Shopping_Mall_Registration_Center_Application_Three {
    public static void main(String[] args) {
        SpringApplication.run(Shopping_Mall_Registration_Center_Application_Three.class,args);
    }
}
