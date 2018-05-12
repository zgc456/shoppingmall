package com.zhkj.dto.order_dto;

import com.zhkj.dto.commodity_dto.CommodityDTO;
import com.zhkj.dto.shoppingcart_dto.ShoppingCartDTO;

import java.util.List;

public class OrderFromPage_Dto {
    private List<ShoppingCartDTO> shoppingCartDTO;
    private List<CommodityDTO> commodityDTO;

    public List<ShoppingCartDTO> getShoppingCartDTO() {
        return shoppingCartDTO;
    }

    public void setShoppingCartDTO(List<ShoppingCartDTO> shoppingCartDTO) {
        this.shoppingCartDTO = shoppingCartDTO;
    }

    public List<CommodityDTO> getCommodityDTO() {
        return commodityDTO;
    }

    public void setCommodityDTO(List<CommodityDTO> commodityDTO) {
        this.commodityDTO = commodityDTO;
    }


}
