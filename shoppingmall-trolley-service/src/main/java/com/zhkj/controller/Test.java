package com.zhkj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;

/**
  这个包和类可以随便删除
  仅仅是为了测试共享session
 */
@RestController
public class Test {
    @RequestMapping("/tests")
    public Object test(HttpSession session){
        System.out.println(session.getAttribute("name"));
        return session.getAttribute("name");
    }

}
