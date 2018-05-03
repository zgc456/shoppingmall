package com.zhkj.dto.order_dto;

public class Discount_Dto {
    /**
     * 商品价格
     */
    private Double shopPrice;
    /**
     * 商品数量
     */
    private Integer shopNumber;

    public Double getShopPrice() {
        return shopPrice;
    }

    public void setShopPrice(Double shopPrice) {
        this.shopPrice = shopPrice;
    }

    public Integer getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(Integer shopNumber) {
        this.shopNumber = shopNumber;
    }
}
