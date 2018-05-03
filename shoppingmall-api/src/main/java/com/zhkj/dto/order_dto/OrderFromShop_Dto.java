package com.zhkj.dto.order_dto;

import java.math.BigDecimal;

public class OrderFromShop_Dto {
    private int id;
    private Integer feight;
    private double commodityPrice;
    private Integer commodityNumber;
    private Integer logisticsTypeId;
    private Integer commodityId;
    private String orderFromId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getFeight() {
        return feight;
    }

    public void setFeight(Integer feight) {
        this.feight = feight;
    }

    public double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public Integer getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(Integer commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    public Integer getLogisticsTypeId() {
        return logisticsTypeId;
    }

    public void setLogisticsTypeId(Integer logisticsTypeId) {
        this.logisticsTypeId = logisticsTypeId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getOrderFromId() {
        return orderFromId;
    }

    public void setOrderFromId(String orderFromId) {
        this.orderFromId = orderFromId;
    }
}
