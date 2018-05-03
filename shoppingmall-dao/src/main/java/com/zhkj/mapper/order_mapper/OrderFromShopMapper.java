package com.zhkj.mapper.order_mapper;

import com.zhkj.entity.Orderfromshop0Entity;
import com.zhkj.entity.Orderfromshop1Entity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单号对应商品
 */
@Repository
public interface OrderFromShopMapper {
     /**
      * 添加数据库
      * @param orderfromshop0Entity
      */
     void insert(Orderfromshop0Entity orderfromshop0Entity);

     /**
      * 添加商品
      * @param orderfromshop0Entity
      * @return
      */
     void insertShop(@Param("orderFromShop") Orderfromshop0Entity orderfromshop0Entity);

     /**
      * 根据订单号查询商品
      * @param orderFromNumber 订单号
      * @return 查询的商品
      */
     List<Orderfromshop0Entity> selectByOrderFrom(@Param("orderFromNumber") String orderFromNumber);

     /**
      * 查看数据库
      * @return
      */
     List<Orderfromshop0Entity> selectAllToOrderfromshop0();

     /**
      * 查看数据库
      * @return
      */
     List<Orderfromshop1Entity> selectAllToOrderfromshop1();
}
