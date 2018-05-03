package com.zhkj.mapper.seek_mapper;

import com.zhkj.entity.CommodityTypeRelationEntity;

import java.util.List;

/**
 * 商品分类关系表接口
 */
public interface CommodityTypeRelationMapper {
    CommodityTypeRelationEntity findByCommodityId(Long id);
    List<CommodityTypeRelationEntity> findByTypeId(List<Long> ids);
}
