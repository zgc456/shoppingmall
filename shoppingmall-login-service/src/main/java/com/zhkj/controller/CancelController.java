package com.zhkj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
public class CancelController {
    @Autowired
    StringRedisTemplate redisTemplate;
    @RequestMapping("/cancel")
    public String cancel(HttpSession session){
//       redisTemplate.opsForValue().set("user_info",null);
       redisTemplate.delete("user_info");
        return "注销成功";
    }
}
