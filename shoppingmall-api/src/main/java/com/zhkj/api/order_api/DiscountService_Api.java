package com.zhkj.api.order_api;


import com.zhkj.dto.order_dto.Discount_Dto;
import com.zhkj.vo.order_vo.Discount_Vo;

public interface DiscountService_Api {
    /**
     * 计算商品价格
     * @return
     */
    Double calculatePrice(Discount_Vo discount_vo);
}
