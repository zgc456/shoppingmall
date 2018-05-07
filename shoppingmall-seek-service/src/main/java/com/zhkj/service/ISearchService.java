package com.zhkj.service;

import com.zhkj.service.entity.CommodityTemplate;
import com.zhkj.service.entity.SearchCondition;
import com.zhkj.service.entity.Test;
import com.zhkj.util.ServiceMultiResult;

public interface ISearchService {
    ServiceMultiResult<CommodityTemplate> byConditionSearch(SearchCondition searchCondition);
    ServiceMultiResult<CommodityTemplate> search(SearchCondition searchCondition);
    void searchEay(Test test);
}
