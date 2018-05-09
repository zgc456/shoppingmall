package com.zhkj.mapper.activity_mapper;

import com.zhkj.entity.PromotionitemEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionitemMapper {
    /**
     * 查询今天的促销商品
     * @param todayTime 今天的日期
     * @return 今天商品集合
     */
    List<PromotionitemEntity> selectByTodayShop(@Param("todayTime") String todayTime);

    /**
     * 根据商品 id 修改商品数量
     * @return 是否成功
     */
    int updateById(@Param("pro") PromotionitemEntity promotionitemEntity);
}
