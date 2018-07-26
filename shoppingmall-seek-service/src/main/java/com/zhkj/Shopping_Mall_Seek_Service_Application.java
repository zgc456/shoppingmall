package com.zhkj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * 搜索业务逻辑模块启动类
 */
//@MapperScan("com.zhkj.mapper")
//@EnableEurekaServer
//@EnableCircuitBreaker
//@EnableHystrixDashboard
//@EnableDiscoveryClient  //去注册中心发现服务
//@EnableRedisHttpSession
@SpringBootApplication
public class Shopping_Mall_Seek_Service_Application {
    public static void main(String[] args) {
        SpringApplication.run(Shopping_Mall_Seek_Service_Application.class,args);
    }
}
