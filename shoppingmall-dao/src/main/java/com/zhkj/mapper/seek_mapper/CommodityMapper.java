package com.zhkj.mapper.seek_mapper;

import com.zhkj.entity.CommodityEntity;

import java.util.List;

/**
 * 商品表接口
 */
public interface CommodityMapper {
    CommodityEntity findByCommodityId(Long id);
    List<CommodityEntity> findByCommodityId(List<Long> ids);
}
