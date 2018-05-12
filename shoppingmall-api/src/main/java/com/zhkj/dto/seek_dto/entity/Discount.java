package com.zhkj.dto.seek_dto.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 16:13 2018/5/12 0012
 */
public class Discount {
    private int id;
    private BigDecimal discountPrice;
    private String discountIntroduce;
    private Integer discountTypeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
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
}
