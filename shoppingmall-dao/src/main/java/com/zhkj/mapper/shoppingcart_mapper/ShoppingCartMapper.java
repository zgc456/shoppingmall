package com.zhkj.mapper.shoppingcart_mapper;

import com.zhkj.entity.ShoppingcartEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface ShoppingCartMapper {
    List<ShoppingcartEntity> queryShoppingCart(Integer userId);
    int saveShoppingCart(Map<String,Object> map);
    int deleteShoppingCart(Map<String,Object> map);
    int updateShoppingCart(Map<String,Object> map);
}
