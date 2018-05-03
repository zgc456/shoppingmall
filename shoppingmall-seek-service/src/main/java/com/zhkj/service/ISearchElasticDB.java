package com.zhkj.service;

/**
 * 查询elastic types下的数据
 */
public interface ISearchElasticDB {
    void search_Commodity(Integer id);
    void search_Commoditytyperelation(Integer id);
    void search_Specificationsrelation(Integer id);
    void search_Specificationstopic(Integer id);
    void search_Specificationsdetailed(Integer id);
    void search_Discount(Integer id);
    void search_Type(Integer id);
}
