package com.zhkj.service.getDB;

import com.zhkj.service.entity.SearchCondotionServiceVO;

/**
 * 查询elastic types下的数据
 */
public interface ISearchElasticDB {
    /**
     * piPiXia
     * @Author: Jiankang.Ren
     * @Description: 根据查询条件查询商品表
     * @Date: 11:18 2018/5/9 0009
     * @param searchCondotionServiceVO

     */
    String search_Commodity(SearchCondotionServiceVO searchCondotionServiceVO);
    /**
     * piPiXia
     * @Author: Jiankang.Ren
     * @Description: 根据条件查询商品分类数据
     * @Date: 11:18 2018/5/9 0009
     * @param searchCondotionServiceVO

     */
    void search_Commoditytyperelation(SearchCondotionServiceVO searchCondotionServiceVO);
    /**
     * piPiXia
     * @Author: Jiankang.Ren
     * @Description: 根据查询条件获取对象 查询商品与商品详细的关系
     * @Date: 11:15 2018/5/9 0009
     * @param searchCondotionServiceVO
     */
    void search_Specificationsrelation(SearchCondotionServiceVO searchCondotionServiceVO);
    /**
     * piPiXia
     * @Author: Jiankang.Ren
     * @Description:
     * @Date: 11:20 2018/5/9 0009
     * @param searchCondotionServiceVO

     */
    void search_Specificationstopic(SearchCondotionServiceVO searchCondotionServiceVO);
    /**
     * piPiXia
     * @Author: Jiankang.Ren
     * @Description: 根据条件查询商品规格
     * @Date: 11:21 2018/5/9 0009
     * @param searchCondotionServiceVO
     */
    void search_Specificationsdetailed(SearchCondotionServiceVO searchCondotionServiceVO);
    /**
     * piPiXia
     * @Author: Jiankang.Ren
     * @Description: 根据条件查询商品折扣
     * @Date: 11:22 2018/5/9 0009
     * @param searchCondotionServiceVO

     */
    void search_Discount(SearchCondotionServiceVO searchCondotionServiceVO);

    /**
     * 根据条件查询商品类型
     * @param searchCondotionServiceVO
     */
    void search_Type(SearchCondotionServiceVO searchCondotionServiceVO);

    /**
     * piPiXia
     * @Author: Jiankang.Ren
     * @Description: 根据id删除商品和所有数据
     * @Date: 11:24 2018/5/9 0009
     * @param id

     */
    void searchDelete(String id);

    /**
     * test的index 测试删除接口
     * @param name
     */
    void searchDeleteByQuery(String name);
}
