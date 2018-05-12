package com.zhkj.api.shoppingcart_api;

import com.zhkj.dto.shoppingcart_dto.ShoppingCartDTO;
import com.zhkj.vo.shoppingCart_vo.ShoppingCartVO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ShoppingCartService {
    @GetMapping("/queryShoppingCart")
    List<ShoppingCartDTO> queryShoppingCart(Integer userId);
    @GetMapping("/saveShoppingCart")
    int saveShoppingCart(ShoppingCartVO shoppingCartVO);
    @GetMapping("/deleteShoppingCart")
    int deleteShoppingCart(ShoppingCartVO shoppingCartVO);
    @GetMapping("/updateShoppingCart")
    int updateShoppingCart(ShoppingCartVO shoppingCartVO);
}
