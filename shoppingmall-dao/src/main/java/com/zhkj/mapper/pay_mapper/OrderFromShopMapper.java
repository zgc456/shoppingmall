package com.zhkj.mapper.pay_mapper;

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
     void insert(Orderfromshop0Entity orderfromshop0Entity);
     List<Orderfromshop0Entity> selectAllToOrderfromshop0();
     List<Orderfromshop1Entity> selectAllToOrderfromshop1();
}
