package com.zhkj.entity;

import java.math.BigDecimal;

public class ShoppingcartEntity {
    private int shopId;
    private Integer commodityNumber;
    private Integer commodityId;
    private String commodityName;
    private Integer commoditySipId;
    private Integer userId;
    private Double commodityPrice;
    private CommoditySpecificationInventoryPriceEntity commoditySpecificationInventoryPriceEntity;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
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

    public CommoditySpecificationInventoryPriceEntity getCommoditySpecificationInventoryPriceEntity() {
        return commoditySpecificationInventoryPriceEntity;
    }

    public void setCommoditySpecificationInventoryPriceEntity(CommoditySpecificationInventoryPriceEntity commoditySpecificationInventoryPriceEntity) {
        this.commoditySpecificationInventoryPriceEntity = commoditySpecificationInventoryPriceEntity;
    }

    public Double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingcartEntity that = (ShoppingcartEntity) o;

        if (shopId != that.shopId) return false;
        if (commodityNumber != null ? !commodityNumber.equals(that.commodityNumber) : that.commodityNumber != null)
            return false;
        if (commodityId != null ? !commodityId.equals(that.commodityId) : that.commodityId != null) return false;
        if (commodityName != null ? !commodityName.equals(that.commodityName) : that.commodityName != null)
            return false;
        if (commoditySipId != null ? !commoditySipId.equals(that.commoditySipId) : that.commoditySipId != null)
            return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (commodityPrice != null ? !commodityPrice.equals(that.commodityPrice) : that.commodityPrice != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = shopId;
        result = 31 * result + (commodityNumber != null ? commodityNumber.hashCode() : 0);
        result = 31 * result + (commodityId != null ? commodityId.hashCode() : 0);
        result = 31 * result + (commodityName != null ? commodityName.hashCode() : 0);
        result = 31 * result + (commoditySipId != null ? commoditySipId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (commodityPrice != null ? commodityPrice.hashCode() : 0);
        return result;
    }
}
