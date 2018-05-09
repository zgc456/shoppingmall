package com.zhkj.service.entity;

/**
 * 商品关键字
 */
public class CommodityKey {
    /**
     * elastic索引
     */
    public static final String INDEX="shoppingmall";
    public static final String ID="id";//每个表都会有的ID
    /**
     * 商品表types
     */
    public static final String TYPES_COMMODITY="commodity";
    public static final String COMMODITY_NAME="commodityname";//商品名称
    public static final String COMMODITY_INTRODUCE="commodityintroduce";//商品介绍
    public static final String COMMODITY_TYPE_RELATION_ID="commoditytyperelationid";//商品类型
    /**
     * 商品规格关系types OR 商品详情
     */
    public static final String TYPES_SPECIFICATIONSRELATION="specificationsrelation";
    public static final String COMMODITY_PRICE="commodityprice";//商品价格
    public static final String COMMODITY_ID="commodityid";
    public static final String TYPEID="typeid";
    public static final String SPECI_TOPIC_ID="specitopicid";//商品规格标题
    public static final String SPECI_DETA_ILFED_ID="specidetailfedid";//商品详细规格介绍
    /**
     * 折扣types
     */
    public static final String TYPES_DISCOUNT="discount";
    public static final String DISCOUNT_INTRODUCE="discountIntroduce";//商品折扣介绍

    /**
     * 商品类型tyeps
     */
    public static final String TYPES_TYPE="type";
    public static final String TYPE_NAME="typename";//商品类型

    /**
     * 商品类型关系types
     */
    public static final String TYPES_COMMODITYTYPERELATION="commoditytyperelation";
    /**
     * 商品规格types
     */
    public static final String TYPES_SPECIFICATIONSTOPIC="specificationstopic";

    /**
     * 商品规格详细types
     */
    public static final String TYPES_SPECIFICATIONSDETAILED="specificationsdetailed";
}
