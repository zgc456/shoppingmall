package com.zhkj.entity;

/**
 * Created by lenovo on 2018/4/16.
 */
public class ShoppingcartEntity {
    private int id;
    private Integer commdityNumber;
    private Integer commdityPrice;
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

    public Integer getCommdityPrice() {
        return commdityPrice;
    }

    public void setCommdityPrice(Integer commdityPrice) {
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

        if (id != that.id) return false;
        if (commdityNumber != null ? !commdityNumber.equals(that.commdityNumber) : that.commdityNumber != null)
            return false;
        if (commdityPrice != null ? !commdityPrice.equals(that.commdityPrice) : that.commdityPrice != null)
            return false;
        if (commdityId != null ? !commdityId.equals(that.commdityId) : that.commdityId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (commdityNumber != null ? commdityNumber.hashCode() : 0);
        result = 31 * result + (commdityPrice != null ? commdityPrice.hashCode() : 0);
        result = 31 * result + (commdityId != null ? commdityId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
