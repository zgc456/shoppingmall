package com.zhkj.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class DiscountEntity {
    private int id;
    private Double discountPrice;
    private String discountIntroduce;
    private Integer discountTypeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDiscountIntroduce() {
        return discountIntroduce;
    }

    public void setDiscountIntroduce(String discountIntroduce) {
        this.discountIntroduce = discountIntroduce;
    }

    public Integer getDiscountTypeId() {
        return discountTypeId;
    }

    public void setDiscountTypeId(Integer discountTypeId) {
        this.discountTypeId = discountTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountEntity that = (DiscountEntity) o;
        return id == that.id &&
                Objects.equals(discountPrice, that.discountPrice) &&
                Objects.equals(discountIntroduce, that.discountIntroduce) &&
                Objects.equals(discountTypeId, that.discountTypeId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, discountPrice, discountIntroduce, discountTypeId);
    }
}
