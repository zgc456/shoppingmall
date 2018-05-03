package com.zhkj.entity;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2018/4/16.
 */
public class OrderfromEntity {
    private int id;
    private String orderNumber;
    private Timestamp orderCreationTime;
    private Integer userId;
    private Integer paymentTypeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Timestamp getOrderCreationTime() {
        return orderCreationTime;
    }

    public void setOrderCreationTime(Timestamp orderCreationTime) {
        this.orderCreationTime = orderCreationTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderfromEntity that = (OrderfromEntity) o;

        if (id != that.id) return false;
        if (orderNumber != null ? !orderNumber.equals(that.orderNumber) : that.orderNumber != null) return false;
        if (orderCreationTime != null ? !orderCreationTime.equals(that.orderCreationTime) : that.orderCreationTime != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (paymentTypeId != null ? !paymentTypeId.equals(that.paymentTypeId) : that.paymentTypeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orderNumber != null ? orderNumber.hashCode() : 0);
        result = 31 * result + (orderCreationTime != null ? orderCreationTime.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (paymentTypeId != null ? paymentTypeId.hashCode() : 0);
        return result;
    }
}
