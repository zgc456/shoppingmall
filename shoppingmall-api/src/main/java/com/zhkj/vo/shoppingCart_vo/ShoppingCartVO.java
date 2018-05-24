package com.zhkj.vo.shoppingCart_vo;

import java.util.List;

public class ShoppingCartVO {
    private Integer commodityNumber;
    private Integer commodityId;
    private String commodityName;
    private Integer commoditySipId;
    private Integer userId;
    private Double  commodityPrice;
    private List list;

    public Integer getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(Integer commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Integer getCommoditySipId() {
        return commoditySipId;
    }

    public void setCommoditySipId(Integer commoditySipId) {
        this.commoditySipId = commoditySipId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
