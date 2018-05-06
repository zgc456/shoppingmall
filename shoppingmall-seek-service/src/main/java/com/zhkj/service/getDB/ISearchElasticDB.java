package com.zhkj.service.getDB;

/**
 * 查询elastic types下的数据
 */
public interface ISearchElasticDB {
    void search_Commodity(String id);
    void search_Commoditytyperelation(String id);
    void search_Specificationsrelation(String id);
    void search_Specificationstopic(String id);
    void search_Specificationsdetailed(String id);
    void search_Discount(String id);
    void search_Type(String id);

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
