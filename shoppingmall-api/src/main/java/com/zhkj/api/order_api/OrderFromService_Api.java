package com.zhkj.api.order_api;

import com.zhkj.dto.order_dto.OrderFrom_Dto;
import com.zhkj.vo.order_vo.OrderFromShop_Vo;
import com.zhkj.vo.order_vo.OrderFrom_Vo;

import java.util.HashMap;
import java.util.List;

public interface OrderFromService_Api {

    /**
     * 添加订单
     * @return 是否成功
     */
    boolean additionOrderFrom(OrderFrom_Vo orderFrom_vo);

    /**
     * 根据用户id查询订单
     * @return 查询后的订单
     */
    HashMap<String,Object> selectUserOrderFrom(OrderFrom_Vo orderFrom_vo);
}
