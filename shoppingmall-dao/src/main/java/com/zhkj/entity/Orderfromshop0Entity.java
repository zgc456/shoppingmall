package com.zhkj.entity;

/**
 * Created by lenovo on 2018/4/16.
 */
public class Orderfromshop0Entity {
    private int id;
    private Integer feight;
    private Integer commodityPrice;
    private Integer commodityNumber;
    private Integer logisticsTypeId;
    private Integer commodityId;
    private Integer orderFromId;

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

    public Integer getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Integer commodityPrice) {
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

    public Integer getOrderFromId() {
        return orderFromId;
    }

    public void setOrderFromId(Integer orderFromId) {
        this.orderFromId = orderFromId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orderfromshop0Entity that = (Orderfromshop0Entity) o;

        if (id != that.id) return false;
        if (feight != null ? !feight.equals(that.feight) : that.feight != null) return false;
        if (commodityPrice != null ? !commodityPrice.equals(that.commodityPrice) : that.commodityPrice != null)
            return false;
        if (commodityNumber != null ? !commodityNumber.equals(that.commodityNumber) : that.commodityNumber != null)
            return false;
        if (logisticsTypeId != null ? !logisticsTypeId.equals(that.logisticsTypeId) : that.logisticsTypeId != null)
            return false;
        if (commodityId != null ? !commodityId.equals(that.commodityId) : that.commodityId != null) return false;
        if (orderFromId != null ? !orderFromId.equals(that.orderFromId) : that.orderFromId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (feight != null ? feight.hashCode() : 0);
        result = 31 * result + (commodityPrice != null ? commodityPrice.hashCode() : 0);
        result = 31 * result + (commodityNumber != null ? commodityNumber.hashCode() : 0);
        result = 31 * result + (logisticsTypeId != null ? logisticsTypeId.hashCode() : 0);
        result = 31 * result + (commodityId != null ? commodityId.hashCode() : 0);
        result = 31 * result + (orderFromId != null ? orderFromId.hashCode() : 0);
        return result;
    }
}
