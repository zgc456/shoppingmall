package com.zhkj.mapper.commoditySpeInvPrice_mapper;


import com.zhkj.entity.CommoditySpecificationInventoryPriceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommoditySpeInvPriceMapper {
    /**
     * 根据id查询出价格和库存
     * @param map
     * @return
     */
    List<CommoditySpecificationInventoryPriceEntity> queryByInvPriceId(Map<String, Object> map);
}
