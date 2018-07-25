package com.zhkj.mapper.shoppingcart_mapper;

import com.zhkj.entity.ShoppingcartEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 购物车
 */
@Repository
public interface ShoppingCartMapper {
    /**
     * 根据userid查询购物车及商品详情
     * @param map 放的是userId
     * @return
     */
    List<ShoppingcartEntity> queryShoppingCart(Map<String, Object> map);

    /**
     * 根据userId查询购物车，只查询购物车信息
     * @param userId
     * @return
     */
    List<ShoppingcartEntity> queryShoppingCartByUserId(Integer userId);

    /**
     * 加入购物车
     * @param map
     * @return
     */
    int saveShoppingCart(Map<String, Object> map);

    /**
     * 根据commoditySipId删除购物车
     * @param map
     * @return
     */
    int deleteShoppingCart(Map<String, Object> map);

    /**
     * 根据commoditySipId和userid更新购物车商品数量
     * @param map
     * @return
     */
    int updateShoppingCart(Map<String, Object> map);
}
