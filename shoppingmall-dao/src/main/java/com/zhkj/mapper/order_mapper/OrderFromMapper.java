package com.zhkj.mapper.order_mapper;

import com.zhkj.entity.CommodityEntity;
import com.zhkj.entity.OrderfromEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderFromMapper {
    /**
     * 添加订单
     * @return
     */
    void saveOrderFrom(@Param("orderFrom") OrderfromEntity orderfromEntity);

    /**
     * 查看特定用户的订单
     * @return
     */
    List<OrderfromEntity> selectOrderFromByUserId(@Param("userId")Integer userId);

    /**
     * 修改特定订单
     * @param number 订单号
     * @return
     */
    void updateByOrderFromNumber(@Param("orderFromNumber")String number);

    /**
     * 查询大于当前时间数据
     * @param nowTime 当前时间
     * @return
     */
    List<OrderfromEntity> selectByNowTime(@Param("nowTime")String nowTime);
    /**
     * 根据商品id查询商品
     * @param commodityId 商品id
     * @return 商品信息
     */
    CommodityEntity selectByCommodityId(@Param("commodityId")Integer commodityId);

}
