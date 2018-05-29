package com.zhkj.controller;

import com.zhkj.register_service.RegisterImpl;
import com.zhkj.vo.login_vo.User_vo;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by haoyu on 2018/4/9.
 */
@RestController
@CrossOrigin
public class RegisterController {
    @Autowired
    RegisterImpl registerApi;
    @Autowired
    StringRedisTemplate redisTemplate;
    @RequestMapping("/s")
    public String cs(){
        redisTemplate.opsForValue().set("info_code","1730");
        System.out.println(redisTemplate.opsForValue().get("info_code"));
        return "成功获取验证码";
    }
    /**
     * 短信验证
     * */
    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";//短信接口的网站
    @RequestMapping("/send")
    public void SendMsg(HttpSession session,HttpServletRequest request) {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);
        client.getParams().setContentCharset("UTF-8");
        String phone_id=request.getParameter("phone");//request.getParameter ("phone_id");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");
        int mobile_code = (int)((Math.random() * 9.0D + 1.0D) * 100000.0D);//随机的验证码
        redisTemplate.opsForValue().set("info_code",String.valueOf(mobile_code));
        String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");//要发的信息
        NameValuePair[] data = new NameValuePair[]{new NameValuePair("account", "C40277015"), new NameValuePair("password", "2d500884588885ad477c124c3d2808d9"), new NameValuePair("mobile", phone_id), new NameValuePair("content", content)};
        method.setRequestBody(data);
        try {
            client.executeMethod(method);
            String SubmitResult = method.getResponseBodyAsString();
            Document doc = DocumentHelper.parseText(SubmitResult);
            Element root = doc.getRootElement();
            String code = root.elementText("code");//状态返回值，2则表示成功
            String msg = root.elementText("msg");//结果描述
            String smsid = root.elementText("smsid");//当提交成功后，此字段为流水号，否则为 0
            System.out.println(code);
            System.out.println(msg);
            System.out.println(smsid);
            if(code == "2") {
                System.out.println("短信提交成功");
            }
        } catch (HttpException var12) {
            var12.printStackTrace();
        } catch (IOException var13) {
            var13.printStackTrace();
        } catch (DocumentException var14) {
            var14.printStackTrace();
        }
    }

    /*
    * 注册
    * */
    @RequestMapping("/save")
    public String save(@ModelAttribute User_vo user_vo, HttpSession session){
        int info_code=0;
        if(redisTemplate.opsForValue().get("info_code")!=null) {
             info_code = Integer.valueOf(redisTemplate.opsForValue().get("info_code"));//获取短信验证码
        }
        if(info_code!=0){
            int code=user_vo.getUserCode();//获取输入的验证码
            if (info_code==code){
               if (user_vo!=null){
                   user_vo.setUserTypeName("普通用户");
                 int a=registerApi.addUser (user_vo);
                 if (a>0){
                     session.setAttribute ("save_msg","注册成功");
                     return "注册成功";
                 }
               }
            }else {
                session.setAttribute ("save_msg","验证码错误");
                return "验证码错误";
            }
            return "失败";
        }
        return "失败";
    }
    /**
     * 判断用户名是否存在
     * */
    @RequestMapping("/selectName")
    public String selectName(HttpServletRequest request){
       String name=null;
       name=request.getParameter("name");
        if(null!=registerApi.selectName(name)){
            return "用户名已经存在";
        }else {
            return "用户名可以使用";
        }
    }
}
