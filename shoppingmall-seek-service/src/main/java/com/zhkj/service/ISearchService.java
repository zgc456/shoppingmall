package com.zhkj.service;

import com.zhkj.service.entity.CommodityDatailsDTO;
import com.zhkj.service.entity.CommodityTemplate;
import com.zhkj.service.entity.SearchConditionPageVO;
import com.zhkj.util.ServiceMultiResult;

import java.util.List;

public interface ISearchService {
    /**
     * 查询所有类型商品
     * @return
     */
    List<ServiceMultiResult<CommodityTemplate>> getAllTypeCommodity();

    /**
     * 查询条件查询商品
     * @param condition 条件为null就查询所有commodity
     * @return
     */
    ServiceMultiResult<CommodityTemplate> byConditionSearchAllCommodity(SearchConditionPageVO condition);

    /**
     * 抢购查询
     * @param searchConditionPageVO
     * @return
     */
    ServiceMultiResult<CommodityTemplate> byDateSearchCommodity(SearchConditionPageVO searchConditionPageVO);

    /**
     * 根据商品ID展示商品详情
     * @param id
     * @return
     */
    CommodityDatailsDTO byIdSearchCommodity(Long id);
}
