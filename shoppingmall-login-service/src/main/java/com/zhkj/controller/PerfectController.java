package com.zhkj.controller;

import com.alibaba.fastjson.JSONArray;
import com.zhkj.dto.login_dto.UserDTO;
import com.zhkj.register_service.PerfectInfoImpl;
import com.zhkj.vo.login_vo.Authenticaion_vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin
public class PerfectController {
    @Autowired
    PerfectInfoImpl perfectInfoAPI;
    @Autowired
    StringRedisTemplate redisTemplate;
    /*
    * 完善信息表添加user表修改
    * */
    @RequestMapping("/perfect")
    public String perfectInfo(@ModelAttribute Authenticaion_vo authenticaion_vo, HttpSession session) {
        String msg=null;
        if (authenticaion_vo != null) {
            String user_if = redisTemplate.opsForValue().get("user_info");
            List<UserDTO> userDTO = JSONArray.parseArray(user_if, UserDTO.class);
            int id = userDTO.get(0).getId();
            int index = perfectInfoAPI.perfectInfo(authenticaion_vo, id);
            if( index>0) {
                msg = "成功页";
            }else
                if (index == 0) {
                    msg = "手机号已绑定其他账号";
                }
        }
        return msg;
        }
    }
