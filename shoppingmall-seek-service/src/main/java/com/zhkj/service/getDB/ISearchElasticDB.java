package com.zhkj.service.getDB;

import com.zhkj.service.entity.SearchCondition;

/**
 * 查询elastic types下的数据
 */
public interface ISearchElasticDB {
    String search_Commodity(SearchCondition searchCondition);
    void search_Commoditytyperelation(SearchCondition searchCondition);
    void search_Specificationsrelation(SearchCondition searchCondition);
    void search_Specificationstopic(SearchCondition searchCondition);
    void search_Specificationsdetailed(SearchCondition searchCondition);
    void search_Discount(SearchCondition searchCondition);
    void search_Type(SearchCondition searchCondition);

    /**
     * 删除接口
     * @param id
     */
    void searchDelete(String id);

    /**
     * test的index 测试删除接口
     * @param name
     */
    void searchDeleteByQuery(String name);
}
