package com.zhkj.api.encrypt_api;

import com.zhkj.dto.encrypt_dto.Encrypt_Returning;

/**
 * 密文业务api
 */
public interface Encrypt_Api {
    /**
     * 用于给模块传输数据
     * @param content 传输数据
     * @param password 私钥
     * @return
     */
    public Encrypt_Returning transmission(String content, String password);

    /**
     * 用于加密接口
     * @return  加密的16进制码
     */
    public String encrypt(String content, String password);

    /**
     * @param content  加密数据
     * @param password 私钥
     * @param encrypt  密文
     * @return
     */
     public boolean cipher_Text_Alignment(String content, String password,String encrypt);
}
