package com.zhkj.mapper.commodity_mapper;

import com.zhkj.entity.CommodityEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommodityMapper {

    List<CommodityEntity> selectAll();
    List<CommodityEntity> selectByTypeId(Map<String, Object> map);
    List<CommodityEntity> selectByCommodityId(Map<String, Object> map);
    List<CommodityEntity> selectCommodity();
    List<CommodityEntity> selectByAllCommodityId(Map<String, Object> map);
}













