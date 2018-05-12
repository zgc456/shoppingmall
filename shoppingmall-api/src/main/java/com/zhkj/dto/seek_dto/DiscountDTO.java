package com.zhkj.dto.seek_dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 14:29 2018/5/11 0011
 */
public class DiscountDTO {
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
