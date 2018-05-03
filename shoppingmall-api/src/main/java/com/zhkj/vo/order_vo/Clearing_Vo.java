package com.zhkj.vo.order_vo;

import com.zhkj.dto.encrypt_dto.Encrypt_Returning;

import java.util.List;

/**
 * 订单模块参数对象
 */
public class Clearing_Vo<T> {
    //总价
    private double price;
    //密文对象
    private Encrypt_Returning encrypt_returning;
    //商品呢数据集合
    private List<T> list;

    public Encrypt_Returning getEncrypt_returning() {
        return encrypt_returning;
    }

    public void setEncrypt_returning(Encrypt_Returning encrypt_returning) {
        this.encrypt_returning = encrypt_returning;
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
