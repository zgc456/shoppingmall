package com.zhkj.mapper.order_mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartMappers {
    /**
     * 根据用户id查询购物车信息
     * @param userId 用户id
     * @return 购物车信息
     */
   // List<ShoppingcartEntity> selectByUserId(@Param("userId") Integer userId);
}
