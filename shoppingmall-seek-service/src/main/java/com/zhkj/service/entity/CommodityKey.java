package com.zhkj.service.entity;

/**
 * 商品关键字
 */
public class CommodityKey {
    /**
     * elastic索引
     */
    public static final String INDEX="shoppingmall";
    /**
     * 商品types
     */
    public static final String TYPES_COMMODITY="commodity";
    public static final String COMMODITY_NAME="commodityName";//商品名称
    public static final String COMMODITY_INTRODUCE="commodityIntroduce";//商品介绍
    /**
     * 商品规格关系types OR 商品详情
     */
    public static final String TYPES_SPECIFICATIONSRELATION="specificationsrelation";
    public static final String COMMODITY_PRICE="commodityPrice";//商品价格
    /**
     * 折扣types
     */
    public static final String TYPES_DISCOUNT="discount";
    public static final String DISCOUNT_INTRODUCE="discountIntroduce";//商品折扣介绍

    /**
     * 商品类型tyeps
     */
    public static final String TYPES_TYPE="type";
    public static final String TYPE_NAME="typeName";//商品类型

    /**
     * 商品类型关系types
     */
    public static final String TYPES_COMMODITYTYPERELATION="COMMODITYTYPERELATION";
    /**
     * 商品规格types
     */
    public static final String TYPES_SPECIFICATIONSTOPIC="specificationstopic";

    /**
     * 商品规格详细types
     */
    public static final String TYPES_SPECIFICATIONSDETAILED="specificationsdetailed";
}
