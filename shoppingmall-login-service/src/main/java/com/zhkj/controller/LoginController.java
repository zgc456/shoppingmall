package com.zhkj.controller;


import com.alibaba.fastjson.JSONArray;
import com.zhkj.dto.login_dto.UserDTO;
import com.zhkj.login_service.LoginImpl;
import com.zhkj.vo.login_vo.User_vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoyu on 2018/4/9.
 */
@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    LoginImpl login;
    @Autowired
    StringRedisTemplate redisTemplate;
    /**
     * 登陆验证验证码于输入的是否一致,账号密码是否正确
     */
    @RequestMapping("/login")
    public String login(@ModelAttribute User_vo user_vo, HttpServletRequest request, HttpSession session) {
           UserDTO userDTO=new UserDTO();
           userDTO=login.selectLogin(user_vo);
            if (userDTO != null) {
//                String userId=String.valueOf(userDTO.getId());
                List<UserDTO>ls=new ArrayList<>();
                ls.add(userDTO);
               // session.setAttribute ("user_info", userDTO);//将用户信息存入session
                JSONArray jsonArray= (JSONArray) JSONArray.toJSON(ls);
                String result=jsonArray.toString();
                redisTemplate.opsForValue().set("user_info",result);
                System.out.println(redisTemplate.opsForValue().get("user_info"));
                return result;
            } else {
                session.setAttribute ("login_msg","账号密码错误");
                return "账号或密码错误";
            }
    }
}
