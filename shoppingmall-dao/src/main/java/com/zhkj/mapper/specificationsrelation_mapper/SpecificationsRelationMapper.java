package com.zhkj.mapper.specificationsrelation_mapper;

import com.zhkj.entity.SpecificationsrelationEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SpecificationsRelationMapper {
    /**
     *根据id查询出商品的库存
     * @param
     * @return
     */
    List<SpecificationsrelationEntity> selectMaxByCommodityId(Map<String, Object> map);
}
