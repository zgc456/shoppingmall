package com.zhkj.controller;

import com.zhkj.api.login_api.LoginApi;
import com.zhkj.dto.login_dto.UserDTO;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by haoyu on 2018/4/9.
 */
@Controller
public class LoginController {
    @Reference
    LoginApi loginApi;

    /*
    * 获取图片验证码
    * */
    @RequestMapping("/photo")
    public void photo(HttpServletRequest request, HttpServletResponse response) {
        BufferedImage bi = new BufferedImage (68, 22, BufferedImage.TYPE_INT_BGR);
        Graphics g = bi.getGraphics ();
//      Color c=new Color(200,150,255);
        g.setColor (Color.lightGray);
        g.fillRect (0, 0, 68, 22);
        char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray ();
        Random r = new Random ();
        int len = ch.length;
        int index;
        StringBuffer sb = new StringBuffer ();
        for (int i = 0; i < 4; i++) {
            index = r.nextInt (len);
            g.setColor (new Color (r.nextInt (88), r.nextInt (188), r.nextInt (255)));
            g.drawString (ch[index] + "", (i * 15), 18);
            sb.append (ch[index]);

        }
        request.getSession ().setAttribute ("piccode", sb.toString ());//验证码
        try {
            ImageIO.write (bi, "JPG", response.getOutputStream ());//图片
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    /**
     * 登陆验证验证码于输入的是否一致,账号密码是否正确
     */
    @RequestMapping("/login")
    public String login(UserDTO userEntity, HttpServletRequest request, HttpSession session) {
        String checkcode = request.getParameter ("checkcode");//获取前台输入的验证码
        String piccode = (String) request.getSession ().getAttribute ("piccode");//获取生成的验证码
        if (checkcode.equals (piccode)) {
            userEntity.setHeadPortraitUrl("E:\\photo");
            java.util.List<UserDTO> ls = loginApi.selectLogin (userEntity);
            if (ls != null) {
                session.setAttribute ("user_info", ls);//将用户信息存入session
            } else {
                System.out.println("账号密码错误");
                session.setAttribute ("login_msg","账号密码错误");
                return "登陆页";
            }
        } else {
            System.out.println("验证码错误");
            session.setAttribute ("login_msg","验证码错误");
            return "登陆页";
        }
        System.out.println("登陆成功");
        return "主页";
    }

}
