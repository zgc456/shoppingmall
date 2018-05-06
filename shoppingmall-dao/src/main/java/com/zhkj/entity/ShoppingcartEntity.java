package com.zhkj.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class ShoppingcartEntity {
    private int id;
    private Integer commdityNumber;
    private BigDecimal commdityPrice;
    private Integer commdityId;
    private Integer userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCommdityNumber() {
        return commdityNumber;
    }

    public void setCommdityNumber(Integer commdityNumber) {
        this.commdityNumber = commdityNumber;
    }

    public BigDecimal getCommdityPrice() {
        return commdityPrice;
    }

    public void setCommdityPrice(BigDecimal commdityPrice) {
        this.commdityPrice = commdityPrice;
    }

    public Integer getCommdityId() {
        return commdityId;
    }

    public void setCommdityId(Integer commdityId) {
        this.commdityId = commdityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingcartEntity that = (ShoppingcartEntity) o;
        return id == that.id &&
                Objects.equals(commdityNumber, that.commdityNumber) &&
                Objects.equals(commdityPrice, that.commdityPrice) &&
                Objects.equals(commdityId, that.commdityId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, commdityNumber, commdityPrice, commdityId, userId);
    }
}
