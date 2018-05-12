package com.zhkj.vo.order_vo;

import java.math.BigDecimal;

public class OrderFrom_Vo {
    /**
     *  订单商品集合
     */
    private OrderFromShop_Vo[] commodityId;

    /**
     * 所属用户
     */
    private Integer userId;
    /**
     * 订单总价
     */
    private double orderFromPrice;
    /**
     * 所属地址
     */
    private Integer harvestAddressId;

    public OrderFromShop_Vo[] getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(OrderFromShop_Vo[] commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public double getOrderFromPrice() {
        return orderFromPrice;
    }

    public void setOrderFromPrice(double orderFromPrice) {
        this.orderFromPrice = orderFromPrice;
    }

    public Integer getHarvestAddressId() {
        return harvestAddressId;
    }

    public void setHarvestAddressId(Integer harvestAddressId) {
        this.harvestAddressId = harvestAddressId;
    }
}
