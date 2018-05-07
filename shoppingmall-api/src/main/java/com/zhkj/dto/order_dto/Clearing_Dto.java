package com.zhkj.dto.order_dto;

import com.zhkj.dto.encrypt_dto.Encrypt_Returning;

import java.util.List;

/**
 * 订单模块返回值对象
 */
public class Clearing_Dto<T> {
    //总价
    private double price;
    //状态
    private int status;
    //商品呢数据集合
    private List<T> list;


    //传输的json 对象 用string表示
    private String json_Name;
    //私钥
    private String private_Key;
    //加密的密文
    private String encrypt;



    public void setEncrypt(String encrypt) {
        this.encrypt = encrypt;
    }



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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
