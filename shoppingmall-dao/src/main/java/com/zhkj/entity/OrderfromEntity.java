package com.zhkj.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class OrderfromEntity {
    private int id;
    private String orderNumber;
    private Timestamp orderCreationTime;
    private Timestamp orderEndTime;
    private Integer userId;
    private Integer paymentTypeId;
    private Double orderfromPrice;
    private Integer harvestAddressId;
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

    public Double getOrderfromPrice() {
        return orderfromPrice;
    }

    public void setOrderfromPrice(Double orderfromPrice) {
        this.orderfromPrice = orderfromPrice;
    }

    public Integer getHarvestAddressId() {
        return harvestAddressId;
    }

    public void setHarvestAddressId(Integer harvestAddressId) {
        this.harvestAddressId = harvestAddressId;
    }

    public String getTransactionNumber() {
        return transactionNumber;
    }

    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderfromEntity that = (OrderfromEntity) o;
        return id == that.id &&
                Objects.equals(orderNumber, that.orderNumber) &&
                Objects.equals(orderCreationTime, that.orderCreationTime) &&
                Objects.equals(orderEndTime, that.orderEndTime) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(paymentTypeId, that.paymentTypeId) &&
                Objects.equals(orderfromPrice, that.orderfromPrice) &&
                Objects.equals(harvestAddressId, that.harvestAddressId) &&
                Objects.equals(transactionNumber, that.transactionNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, orderNumber, orderCreationTime, orderEndTime, userId, paymentTypeId, orderfromPrice, harvestAddressId, transactionNumber);
    }
}
