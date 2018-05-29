package com.zhkj.api.shoppingcart_api;

import com.zhkj.dto.shoppingcart_dto.ShoppingCartDTO;
import com.zhkj.vo.shoppingCart_vo.ShoppingCartVO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ShoppingCartService {
    /**
     * 根据userid查询购物车及商品详情
     * @param shoppingCartVO
     * @return
     */
    @GetMapping("/queryShoppingCart")
    List<ShoppingCartDTO> queryShoppingCart(ShoppingCartVO shoppingCartVO);

    /**
     * 根据userId查询购物车，只查询购物车信息
     * @param userId
     * @return
     */
    List<ShoppingCartDTO> queryShoppingCartByUserId(Integer userId);

    /**
     * 加入购物车
     * @param shoppingCartVO
     * @return
     */
    @GetMapping("/saveShoppingCart")
    int saveShoppingCart(ShoppingCartVO shoppingCartVO);

    /**
     * 根据commoditySipId删除购物车
     * @param shoppingCartVO
     * @return
     */
    @GetMapping("/deleteShoppingCart")
    int deleteShoppingCart(ShoppingCartVO shoppingCartVO);

    /**
     * 根据commoditySipId和userid更新购物车商品数量
     * @param shoppingCartVO
     * @return
     */
    @GetMapping("/updateShoppingCart")
    int updateShoppingCart(ShoppingCartVO shoppingCartVO);
}
