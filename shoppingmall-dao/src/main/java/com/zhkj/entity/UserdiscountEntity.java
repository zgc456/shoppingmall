package com.zhkj.entity;

/**
 * Created by lenovo on 2018/4/16.
 */
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

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (discountId != null ? !discountId.equals(that.discountId) : that.discountId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (discountId != null ? discountId.hashCode() : 0);
        return result;
    }
}
