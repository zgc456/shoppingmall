package com.zhkj.vo.order_vo;
//import com.zhkj.dto.shoppingcart_dto.ShoppingCartDTO;

import java.util.List;

public class OrderFrom_Vo {
//    private List<ShoppingCartDTO> shoppingcartEntityList;
    /**
     * 所属用户
     */
    private Integer userId;
    /**
     * 订单总价
     */
    private double orderFromPrice;
    /**
     * 所属地址
     */
    private Integer harvestAddressId;

//    public List<ShoppingCartDTO> getShoppingcartEntityList() {
//        return shoppingcartEntityList;
//    }
//
//    public void setShoppingcartEntityList(List<ShoppingCartDTO> shoppingcartEntityList) {
//        this.shoppingcartEntityList = shoppingcartEntityList;
//    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public double getOrderFromPrice() {
        return orderFromPrice;
    }

    public void setOrderFromPrice(double orderFromPrice) {
        this.orderFromPrice = orderFromPrice;
    }

    public Integer getHarvestAddressId() {
        return harvestAddressId;
    }

    public void setHarvestAddressId(Integer harvestAddressId) {
        this.harvestAddressId = harvestAddressId;
    }
}
