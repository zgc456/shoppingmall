package com.zhkj.service;

import com.zhkj.service.entity.CommodityTemplate;
import com.zhkj.service.entity.SearchCondition;
import com.zhkj.util.ServiceMultiResult;

public interface ISearchService {
    void index(Long commodityId);
    void remove(Long commodityId);
    ServiceMultiResult<CommodityTemplate> byConditionSearch(SearchCondition searchCondition);
}
