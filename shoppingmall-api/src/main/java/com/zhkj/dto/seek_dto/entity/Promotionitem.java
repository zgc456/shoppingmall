package com.zhkj.dto.seek_dto.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 16:13 2018/5/12 0012
 */
public class Promotionitem {
    private int id;
    private Timestamp startTime;
    private Timestamp endTime;
    private BigDecimal discountPrice;
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

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
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

}
