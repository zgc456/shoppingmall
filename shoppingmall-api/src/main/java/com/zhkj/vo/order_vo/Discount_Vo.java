package com.zhkj.vo.order_vo;

import com.zhkj.dto.order_dto.Discount_Dto;

/**
 *折扣计算参数
 */
public class Discount_Vo {
    private Discount_Dto[] discountDtos;
    private Double discount;

    public Discount_Dto[] getDiscountDtos() {
        return discountDtos;
    }

    public void setDiscountDtos(Discount_Dto[] discountDtos) {
        this.discountDtos = discountDtos;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
