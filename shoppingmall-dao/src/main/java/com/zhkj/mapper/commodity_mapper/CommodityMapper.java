package com.zhkj.mapper.commodity_mapper;

import com.zhkj.entity.CommodityEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommodityMapper {
    /**
     * 查询所有商品
     * @return
     */
    List<CommodityEntity> selectAll();

    /**
     * 根据商品类型查询
     * @param map
     * @return
     */
    List<CommodityEntity> selectByTypeId(Map<String, Object> map);

    /**
     * 根据商品id查询
     * @param map
     * @return
     */
    List<CommodityEntity> selectByCommodityId(Map<String, Object> map);

    /**
     * 查询所有商品
     * @return
     */
    List<CommodityEntity> selectCommodity();


}













