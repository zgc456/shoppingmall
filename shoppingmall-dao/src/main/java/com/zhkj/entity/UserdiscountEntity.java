package com.zhkj.entity;

import java.util.Objects;

public class UserdiscountEntity {
    private int id;
    private Integer userId;
    private Integer discountId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserdiscountEntity that = (UserdiscountEntity) o;
        return id == that.id &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(discountId, that.discountId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, discountId);
    }
}
