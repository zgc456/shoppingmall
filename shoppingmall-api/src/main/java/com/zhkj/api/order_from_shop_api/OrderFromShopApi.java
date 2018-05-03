package com.zhkj.api.order_from_shop_api;

import com.zhkj.vo.order_from_shop_vo.OrderFromVo;

/**
 * 支付成功后订单api
 */
public interface OrderFromShopApi {
    /**
     * 根据订单号修改订单表 添加支付宝交易号
     * @param orderFromShop
     * @return
     */
    public int updateShopApi(OrderFromVo orderFromShop);
}
