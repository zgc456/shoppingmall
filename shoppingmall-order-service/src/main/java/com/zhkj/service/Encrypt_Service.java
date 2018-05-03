package com.zhkj.service;

import com.zhkj.api.encrypt_api.Encrypt_Api;
import com.zhkj.dto.encrypt_dto.Encrypt_Returning;
import com.zhkj.encryption.AES_Util;
import org.springframework.stereotype.Service;

/**
 * 加密业务处理
 */
@Service
public class Encrypt_Service implements Encrypt_Api {
    /**
     * 用于给模块传输数据
     * @param content 传输数据
     * @param password 私钥
     * @return
     */
    @Override
    public Encrypt_Returning transmission(String content, String password) {
        Encrypt_Returning encrypt_returning=new Encrypt_Returning();
        encrypt_returning.setEncrypt(encrypt(content,password));
        encrypt_returning.setJson_Name(content);
        encrypt_returning.setPrivate_Key(password);
        return encrypt_returning;
    }

    /**
     * 加密
     * @param content 传输的数据
     * @param password 私钥
     * @return 密文
     */
    @Override
    public String encrypt(String content, String password) {
        return AES_Util.encrypt(content,password);
    }

    /**
     * 用于接收数据，私钥，密文，比对密文
     * @param content  加密传输的数据
     * @param password 私钥
     * @param encrypt  密文
     * @return 是否比对成功
     */
    @Override
    public boolean cipher_Text_Alignment(String content, String password, String encrypt) {
        System.out.println(encrypt(content,password));
       if ( encrypt(content,password).equals(encrypt)){
           return true;
       }else{
           return false;
       }
    }
}
