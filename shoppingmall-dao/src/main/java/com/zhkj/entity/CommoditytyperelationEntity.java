package com.zhkj.entity;

import java.util.Objects;

public class CommoditytyperelationEntity {
    private int id;
    private Integer shopPrimaryTypeId;
    private Integer shopMinorTypeId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommoditytyperelationEntity that = (CommoditytyperelationEntity) o;
        return id == that.id &&
                Objects.equals(shopPrimaryTypeId, that.shopPrimaryTypeId) &&
                Objects.equals(shopMinorTypeId, that.shopMinorTypeId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, shopPrimaryTypeId, shopMinorTypeId);
    }
}
