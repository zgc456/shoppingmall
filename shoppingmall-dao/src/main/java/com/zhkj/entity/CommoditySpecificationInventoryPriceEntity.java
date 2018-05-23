package com.zhkj.entity;

import java.math.BigDecimal;

public class CommoditySpecificationInventoryPriceEntity {
    private int id;
    private int commodityId;
    private int specification1;
    private Integer specification2;
    private Integer specification3;
    private Integer specification4;
    private int inventory;
    private Double price;
    private String picture;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getSpecification1() {
        return specification1;
    }

    public void setSpecification1(int specification1) {
        this.specification1 = specification1;
    }

    public Integer getSpecification2() {
        return specification2;
    }

    public void setSpecification2(Integer specification2) {
        this.specification2 = specification2;
    }

    public Integer getSpecification3() {
        return specification3;
    }

    public void setSpecification3(Integer specification3) {
        this.specification3 = specification3;
    }

    public Integer getSpecification4() {
        return specification4;
    }

    public void setSpecification4(Integer specification4) {
        this.specification4 = specification4;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommoditySpecificationInventoryPriceEntity that = (CommoditySpecificationInventoryPriceEntity) o;

        if (id != that.id) return false;
        if (commodityId != that.commodityId) return false;
        if (specification1 != that.specification1) return false;
        if (inventory != that.inventory) return false;
        if (specification2 != null ? !specification2.equals(that.specification2) : that.specification2 != null)
            return false;
        if (specification3 != null ? !specification3.equals(that.specification3) : that.specification3 != null)
            return false;
        if (specification4 != null ? !specification4.equals(that.specification4) : that.specification4 != null)
            return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + commodityId;
        result = 31 * result + specification1;
        result = 31 * result + (specification2 != null ? specification2.hashCode() : 0);
        result = 31 * result + (specification3 != null ? specification3.hashCode() : 0);
        result = 31 * result + (specification4 != null ? specification4.hashCode() : 0);
        result = 31 * result + inventory;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        return result;
    }
}
