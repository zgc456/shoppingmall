package com.zhkj.vo.order_vo;

import java.math.BigDecimal;

/**
 * 订单的商品
 */
public class OrderFromShop_Vo {
    /**
     * 运费
     */
    private Integer feight;
    /**
     * 订单商品的价格
     */
    private double commodityPrice;
    /**
     * 订单商品的数量
     */
    private Integer commodityNumber;
    /**
     * 订单对应的商品
     */
    private Integer commodityId;

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

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }
}
