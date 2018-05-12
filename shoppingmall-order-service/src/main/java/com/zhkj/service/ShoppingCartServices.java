package com.zhkj.service;

import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.order_dto.OrderFromPage_Dto;
import com.zhkj.dto.order_dto.OrderFrom_Dto;
import com.zhkj.entity.CommodityEntity;
import com.zhkj.entity.ShoppingcartEntity;
import com.zhkj.mapper.order_mapper.HarvestAddressMapper;
import com.zhkj.mapper.order_mapper.OrderFromMapper;
import com.zhkj.mapper.order_mapper.ShoppingCartMappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ShoppingCartServices {
    @Autowired
    private ShoppingCartMappers shoppingCartMapper;
    @Autowired
    private HarvestAddressMapper harvestAddressMapper;
    @Autowired
    private OrderFromMapper orderFromMapper;
    public OrderFromPage_Dto gainUserInformation(OrderFrom_Dto orderFrom_dto){
        OrderFromPage_Dto orderFromPage_dto = new OrderFromPage_Dto();
        if(null != orderFrom_dto){
            List<CommodityEntity> commodityEntities = new ArrayList<>();
            List<ShoppingcartEntity> entityList = shoppingCartMapper.selectByUserId(orderFrom_dto.getUserId());
            //HarvestaddressEntity harvestaddressEntity = harvestAddressMapper.selectByUserAndHarvestAddressId(orderFrom_dto.getUserId(),orderFrom_dto.getHarvestAddressId());
            entityList.forEach(
                    shoppingCart->{
                        CommodityEntity commodityEntity = orderFromMapper.selectByCommodityId(shoppingCart.getCommdityId());
                        commodityEntities.add(commodityEntity);
                    }
            );
            //orderFromPage_dto.setHarvestaddressEntity_dto(Conver_Type.convert(orderFromPage_dto.getHarvestaddressEntity_dto(),harvestaddressEntity));
            orderFromPage_dto.setCommodityDTO(Conver_Type.convertToList(orderFromPage_dto.getCommodityDTO(),commodityEntities,"com.zhkj.dto.commodity_dto.CommodityDTO"));
            orderFromPage_dto.setShoppingCartDTO(Conver_Type.convertToList(orderFromPage_dto.getShoppingCartDTO(),entityList,"com.zhkj.dto.shoppingcart_dto.ShoppingCartDTO"));
        }
        return orderFromPage_dto;
    }
}
