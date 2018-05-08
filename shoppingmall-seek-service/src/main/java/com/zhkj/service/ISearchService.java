package com.zhkj.service;

import com.zhkj.service.entity.CommodityTemplate;
import com.zhkj.service.entity.SearchCondition;
import com.zhkj.service.entity.Test;
import com.zhkj.util.ServiceMultiResult;

import java.util.List;

public interface ISearchService {
    ServiceMultiResult<CommodityTemplate> byTypeSearch();
    ServiceMultiResult<CommodityTemplate> search(SearchCondition searchCondition);
    void searchEay(Test test);
}
