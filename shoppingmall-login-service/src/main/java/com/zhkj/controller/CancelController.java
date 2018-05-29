package com.zhkj.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
public class CancelController {
    @RequestMapping("/cancel")
    public String cancel(HttpSession session){
        session.removeAttribute("user_info");
        return "注销成功";
    }
}
