package com.zhkj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 登陆注册模块业务
 */
@MapperScan("com.zhkj.mapper")
@EnableEurekaServer
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@EnableDiscoveryClient  //去注册中心发现服务
@EnableRedisHttpSession //启动httpsession
public class Shopping_Mall_Login_Service_Application {
    public static void main(String[] args) {
        System.out.println("123");
        SpringApplication.run(Shopping_Mall_Login_Service_Application.class,args);
    }
}
