package com.zhkj.service;

import com.zhkj.api.shoppingcart_api.ShoppingCartService;
import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.shoppingcart_dto.ShoppingCartDTO;
import com.zhkj.mapper.shoppingcart_mapper.ShoppingCartMapper;
import com.zhkj.vo.shoppingCart_vo.ShoppingCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Override
    public List queryShoppingCart(ShoppingCartVO shoppingCartVO) {
//        List<ShoppingCartDTO> list=new ArrayList<>();
//        return Conver_Type.convertToList(list,shoppingCartMapper.queryShoppingCart(BeanMap.create(shoppingCartVO)),"com.zhkj.dto.shoppingcart_dto.ShoppingCartDTO");
        return shoppingCartMapper.queryShoppingCart(BeanMap.create(shoppingCartVO));
    }

    @Override
    public List<ShoppingCartDTO> queryShoppingCartByUserId(Integer userId) {
        List<ShoppingCartDTO> list=new ArrayList<>();
        return Conver_Type.convertToList(list,shoppingCartMapper.queryShoppingCartByUserId(userId),"com.zhkj.dto.shoppingcart_dto.ShoppingCartDTO");
    }

    @Override
    public int saveShoppingCart(ShoppingCartVO shoppingCartVO) {
        return shoppingCartMapper.saveShoppingCart(BeanMap.create(shoppingCartVO));
    }

    @Override
    public int deleteShoppingCart(ShoppingCartVO shoppingCartVO) {
        return shoppingCartMapper.deleteShoppingCart(BeanMap.create(shoppingCartVO));
    }

    @Override
    public int updateShoppingCart(ShoppingCartVO shoppingCartVO) {
        return shoppingCartMapper.updateShoppingCart(BeanMap.create(shoppingCartVO));
    }
}
