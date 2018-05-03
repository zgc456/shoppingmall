package com.zhkj.vo.order_vo;

import com.zhkj.dto.encrypt_dto.Encrypt_Returning;

/**
 * 订单参数
 */
public class Order_Vo {
    //总价
    private double price;
    //密文对象
    private Encrypt_Returning encrypt_returning;
    //地址
    private Address_Vo address_vo;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Encrypt_Returning getEncrypt_returning() {
        return encrypt_returning;
    }

    public void setEncrypt_returning(Encrypt_Returning encrypt_returning) {
        this.encrypt_returning = encrypt_returning;
    }

    public Address_Vo getAddress_vo() {
        return address_vo;
    }

    public void setAddress_vo(Address_Vo address_vo) {
        this.address_vo = address_vo;
    }
}
