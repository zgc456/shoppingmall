package com.zhkj.service;

import com.zhkj.service.entity.CommodityTemplate;
import com.zhkj.service.entity.SearchConditionPageVO;
import com.zhkj.service.entity.Test;
import com.zhkj.util.ServiceMultiResult;

import java.util.List;
import java.util.Map;

public interface ISearchService {
    List<ServiceMultiResult<CommodityTemplate>> getAllTypeCommodity();
    ServiceMultiResult<CommodityTemplate> search(SearchConditionPageVO searchCondition);
    void searchEay(Test test);
}
