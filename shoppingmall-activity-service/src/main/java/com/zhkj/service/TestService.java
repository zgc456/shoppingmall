package com.zhkj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("/a")
    public String name(){
        stringRedisTemplate.opsForValue().set("keys","123456");
        return "true";
    }
}
