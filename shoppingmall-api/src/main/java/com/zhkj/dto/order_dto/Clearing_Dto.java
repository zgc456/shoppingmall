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
    //加密返回数据对象
    private Encrypt_Returning encrypt_returning;

    public Encrypt_Returning getEncrypt_returning() {
        return encrypt_returning;
    }

    public void setEncrypt_returning(Encrypt_Returning encrypt_returning) {
        this.encrypt_returning = encrypt_returning;
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
