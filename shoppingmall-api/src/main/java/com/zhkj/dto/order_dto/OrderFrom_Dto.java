package com.zhkj.dto.order_dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 订单信息
 */
public class OrderFrom_Dto {
    private int id;
    private String orderNumber;
    private Timestamp orderCreationTime;
    private Timestamp orderEndTime;
    private Integer userId; //用户名id
    private Integer paymentTypeId;
    private Integer harvestAddressId;//地址id
    private double orderfromPrice;
    private String transactionNumber;

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

    public Timestamp getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Timestamp orderEndTime) {
        this.orderEndTime = orderEndTime;
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

    public Integer getHarvestAddressId() {
        return harvestAddressId;
    }

    public void setHarvestAddressId(Integer harvestAddressId) {
        this.harvestAddressId = harvestAddressId;
    }

    public Double getOrderfromPrice() {
        return orderfromPrice;
    }

    public void setOrderfromPrice(Double orderfromPrice) {
        this.orderfromPrice = orderfromPrice;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }
}
