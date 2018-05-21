package com.zhkj.service;

import com.zhkj.service.entity.CommodityTemplate;
import com.zhkj.service.entity.SearchConditionPageVO;
import com.zhkj.util.ServiceMultiResult;

import java.util.List;

public interface ISearchService {
    List<ServiceMultiResult<CommodityTemplate>> getAllTypeCommodity();
    ServiceMultiResult<CommodityTemplate> search(SearchConditionPageVO searchCondition);
    ServiceMultiResult<CommodityTemplate> byDateSearchCommodity(SearchConditionPageVO searchConditionPageVO);
    ServiceMultiResult<CommodityTemplate> byIdSearchCommodity(Long id);
}
