package com.zhkj.mapper.seek_mapper;

import com.zhkj.entity.DiscountEntity;

import java.util.List;

/**
 * 折扣表接口
 */
public interface DiscountMapper {
    DiscountMapper findByDiscountId(Long id);
    List<DiscountEntity> findByDiscountId(List<Long> ids);
}
