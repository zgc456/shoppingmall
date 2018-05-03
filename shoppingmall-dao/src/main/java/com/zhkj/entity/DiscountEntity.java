package com.zhkj.entity;

/**
 * Created by lenovo on 2018/4/16.
 */
public class DiscountEntity {
    private int id;
    private Integer discountPrice;
    private String discountIntroduce;
    private Integer discountTypeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Integer discountPrice) {
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

        if (id != that.id) return false;
        if (discountPrice != null ? !discountPrice.equals(that.discountPrice) : that.discountPrice != null)
            return false;
        if (discountIntroduce != null ? !discountIntroduce.equals(that.discountIntroduce) : that.discountIntroduce != null)
            return false;
        if (discountTypeId != null ? !discountTypeId.equals(that.discountTypeId) : that.discountTypeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (discountPrice != null ? discountPrice.hashCode() : 0);
        result = 31 * result + (discountIntroduce != null ? discountIntroduce.hashCode() : 0);
        result = 31 * result + (discountTypeId != null ? discountTypeId.hashCode() : 0);
        return result;
    }
}
