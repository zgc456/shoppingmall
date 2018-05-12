package com.zhkj.controller;

import com.zhkj.api.register_api.PerfectInfoAPI;
import com.zhkj.dto.login_dto.AuthenticationDTO;
import com.zhkj.entity.UserEntity;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PerfectController {
    @Reference
    PerfectInfoAPI perfectInfoAPI;
    /*
    * 完善信息表添加user表修改
    * */
    @RequestMapping("/perfect")
    public String perfectInfo(AuthenticationDTO authenticationEntity, HttpSession session){
        if (authenticationEntity!=null){
            List<UserEntity> ls= (List<UserEntity>) session.getAttribute("user_info");
            int id=ls.get(0).getId();
           if( perfectInfoAPI.perfectInfo(authenticationEntity,id)>0){
               return "成功页";
           }
        }
        return "成功页";
    }
}
