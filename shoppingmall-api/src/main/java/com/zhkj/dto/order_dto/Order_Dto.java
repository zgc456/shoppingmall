package com.zhkj.dto.order_dto;

import com.zhkj.dto.encrypt_dto.Encrypt_Returning;
import com.zhkj.vo.order_vo.Address_Vo;

import java.util.List;

/**
 * 订单页dto
 */
public class Order_Dto<T> {
    //状态
    private int status;

    //地址
    private Address_Vo address_vo;

    //总价
    private double price;
    //密文对象
    private Encrypt_Returning encrypt_returning;


    public Encrypt_Returning getEncrypt_returning() {
        return encrypt_returning;
    }

    public void setEncrypt_returning(Encrypt_Returning encrypt_returning) {
        this.encrypt_returning = encrypt_returning;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Address_Vo getAddress_vo() {
        return address_vo;
    }

    public void setAddress_vo(Address_Vo address_vo) {
        this.address_vo = address_vo;
    }
}
