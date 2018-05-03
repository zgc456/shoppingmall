package com.zhkj.mapper.pay_mapper;

import com.zhkj.entity.OrderfromEntity;
import org.springframework.stereotype.Repository;

/**
 * 支付成功后订单操作
 */
@Repository
public interface PayOrderFromMapper {
    /**
     * 修改订单号
     * @return
     */
    public void updateOrderFrom(OrderfromEntity orderfromEntity);
}
