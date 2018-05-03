package com.zhkj.entity;

import java.sql.Timestamp;

/**
 * Created by lenovo on 2018/4/16.
 */
public class PromotionitemEntity {
    private int id;
    private Timestamp startTime;
    private Timestamp endTime;
    private Integer discountPrice;
    private Integer commodityNumber;
    private Integer commodityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Integer discountPrice) {
        this.discountPrice = discountPrice;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PromotionitemEntity that = (PromotionitemEntity) o;

        if (id != that.id) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (endTime != null ? !endTime.equals(that.endTime) : that.endTime != null) return false;
        if (discountPrice != null ? !discountPrice.equals(that.discountPrice) : that.discountPrice != null)
            return false;
        if (commodityNumber != null ? !commodityNumber.equals(that.commodityNumber) : that.commodityNumber != null)
            return false;
        if (commodityId != null ? !commodityId.equals(that.commodityId) : that.commodityId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (discountPrice != null ? discountPrice.hashCode() : 0);
        result = 31 * result + (commodityNumber != null ? commodityNumber.hashCode() : 0);
        result = 31 * result + (commodityId != null ? commodityId.hashCode() : 0);
        return result;
    }
}
