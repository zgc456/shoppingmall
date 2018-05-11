package com.zhkj.mapper.shopping_cart;

import com.zhkj.entity.ShoppingcartEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartMapper {
    /**
     * 根据用户id查询购物车信息
     * @param userId 用户id
     * @return 购物车信息
     */
    List<ShoppingcartEntity > selectByUserId(@Param("userId") Integer userId);
}
