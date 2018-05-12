package com.zhkj.config.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 此配置用于配置redisson
 */
@Configuration
public class RedissonConfig {
    @Bean
    public RedissonClient redisson(){
        Config config = new Config();
        config. useSingleServer().setAddress("redis://39.108.143.176:6379");
        return Redisson.create(config);
    }
}
