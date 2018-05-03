package com.zhkj.entity;

public class CommodityTypeRelationEntity {
    private int id;
    private Integer shopPrimaryTypeId;
    private Integer shopMinorTypeId;
    private Integer commodityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getShopPrimaryTypeId() {
        return shopPrimaryTypeId;
    }

    public void setShopPrimaryTypeId(Integer shopPrimaryTypeId) {
        this.shopPrimaryTypeId = shopPrimaryTypeId;
    }

    public Integer getShopMinorTypeId() {
        return shopMinorTypeId;
    }

    public void setShopMinorTypeId(Integer shopMinorTypeId) {
        this.shopMinorTypeId = shopMinorTypeId;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }
}
