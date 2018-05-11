package com.zhkj.vo.shoppingCart_vo;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartVO {
    private Integer commodityNumber;
    private Double commodityPrice;
    private Integer commodityId;
    private String smallPictureUrl;
    private String commodityIntroduce;
    private Integer userId;
    private List list;

    public Integer getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(Integer commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    public Double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getSmallPictureUrl() {
        return smallPictureUrl;
    }

    public void setSmallPictureUrl(String smallPictureUrl) {
        this.smallPictureUrl = smallPictureUrl;
    }

    public String getCommodityIntroduce() {
        return commodityIntroduce;
    }

    public void setCommodityIntroduce(String commodityIntroduce) {
        this.commodityIntroduce = commodityIntroduce;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
