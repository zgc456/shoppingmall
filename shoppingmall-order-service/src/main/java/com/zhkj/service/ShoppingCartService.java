package com.zhkj.service;

import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.shoppingcart_dto.ShoppingCartDTO;
import com.zhkj.entity.ShoppingcartEntity;
import com.zhkj.mapper.shopping_cart.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    public List<ShoppingCartDTO> gainUserInformation(String stringUserId){
        List<ShoppingCartDTO> shoppingCartDtos = new ArrayList<>();
        if(null != stringUserId && !(stringUserId.equals(""))){
            Integer integerUserId = Integer.parseInt(stringUserId);
            List<ShoppingcartEntity> entityList = shoppingCartMapper.selectByUserId(integerUserId);
            shoppingCartDtos = Conver_Type.convertToList(shoppingCartDtos,entityList,"com.zhkj.dto.shoppingcart_dto.ShoppingCartDTO");
        }
        return shoppingCartDtos;
    }
}
