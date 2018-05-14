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
     * 商品types(commodity表)
     */
    public static final String TYPES_COMMODITY="commodity";
    public static final String COMMODITY_NAME="commodityName";//商品名称
    public static final String COMMODITY_INTRODUCE="commodityIntroduce";//商品介绍
    public static final String COMMODITY_TYPE_RELATION_ID="commodityTypeRelationId";//商品类型

    /**
     * 商品类型关系types(commoditytyperelation表)
     */
    public static final String TYPES_COMMODITYTYPERELATION="commoditytyperelation";

    /**
     * 折扣types(discount表)
     */
    public static final String TYPES_DISCOUNT="discount";
    public static final String DISCOUNT_INTRODUCE="discountIntroduce";//商品折扣介绍

    /**
     * 促销types(promotionitem表)
     */
    public static final String TYPES_PROMOTIONITEM="promotionitem";
    public static final String START_TIME="startTime";//促销开始时间
    public static final String END_TIME="endTime";//促销结束时间


    /**
     * 商品规格详细types(specificationsdetailed表)
     */
    public static final String TYPES_SPECIFICATIONSDETAILED="specificationsdetailed";

    /**
     * 商品规格关系types(specificationsrelation表) OR 商品详情
     */
    public static final String TYPES_SPECIFICATIONSRELATION="specificationsrelation";
    public static final String COMMODITY_PRICE="commodityPrice";//商品价格
    public static final String COMMODITY_ID="commodityId";
    public static final String TYPEID="typeId";
    public static final String SPECI_TOPIC_ID="speciTopicId";//商品规格标题
    public static final String SPECI_DETA_ILFED_ID="speciDetailedId";//商品详细规格介绍

    /**
     * 商品规格types(specificationstopic表)
     */
    public static final String TYPES_SPECIFICATIONSTOPIC="specificationstopic";

    /**
     * 商品类型tyeps(type表)
     */
    public static final String TYPES_TYPE="type";
    public static final String TYPE_NAME="typeName";//商品类型
}
