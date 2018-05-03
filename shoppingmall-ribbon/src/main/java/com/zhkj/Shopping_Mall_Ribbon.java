package com.zhkj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.client.RestTemplate;

/**
 * ribbon负载均衡 调用服务 已经测试成功调用
 * 程序员可二次侧式
 */
@SpringBootApplication
@EnableDiscoveryClient  //去注册中心发现服务
@EnableRedisHttpSession
public class Shopping_Mall_Ribbon {
    @Bean
    @LoadBalanced  //里面有负载均衡机制
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(Shopping_Mall_Ribbon.class,args);
    }
}
