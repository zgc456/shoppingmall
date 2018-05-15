package com.zhkj.vo.order_vo;

import com.zhkj.dto.encrypt_dto.Encrypt_Returning;

import java.util.List;

/**
 * 订单模块参数对象
 */
public class Clearing_Vo<T> {

    //总价
    private double price;

    //商品呢数据集合
    private List<T> list;
    private List<T> address;
    private T lists;

    public T getLists() {
        return lists;
    }

    public void setLists(T lists) {
        this.lists = lists;
    }

    public List<T> getAddress() {
        return address;
    }

    public void setAddress(List<T> address) {
        this.address = address;
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
