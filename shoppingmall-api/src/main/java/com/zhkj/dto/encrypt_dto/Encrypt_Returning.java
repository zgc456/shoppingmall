package com.zhkj.dto.encrypt_dto;

/**
 * 这个类用于传输密文对象的规范
 */
public class Encrypt_Returning {
    //传输的json 对象 用string表示
    private String json_Name;
    //私钥
    private String private_Key;
    //加密的密文
    private String encrypt;

    public String getJson_Name() {
        return json_Name;
    }

    public void setJson_Name(String json_Name) {
        this.json_Name = json_Name;
    }

    public String getPrivate_Key() {
        return private_Key;
    }

    public void setPrivate_Key(String private_Key) {
        this.private_Key = private_Key;
    }

    public String getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }
}
