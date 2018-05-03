package com.zhkj.service;


import com.zhkj.api.order_api.DiscountService_Api;
import com.zhkj.dto.order_dto.Discount_Dto;
import com.zhkj.vo.order_vo.Discount_Vo;
import org.springframework.stereotype.Service;

@Service
public class DiscountService implements DiscountService_Api {
    /**
     * 计算总价
     * @param discount_vo
     * @return
     */
    @Override
    public Double calculatePrice(Discount_Vo discount_vo) {
        Double totalPrice = 0.0;
        if(null != discount_vo.getDiscountDtos() && discount_vo.getDiscountDtos().length > 0){
            for(int i = 0;i < discount_vo.getDiscountDtos().length;i++){
                Double unitPrice = 0.0;
                unitPrice = discount_vo.getDiscountDtos()[i].getShopPrice() * discount_vo.getDiscountDtos()[i].getShopNumber();
                totalPrice = totalPrice + unitPrice;
            }
            totalPrice = totalPrice * discount_vo.getDiscount();
        }
        return totalPrice;
    }
}
