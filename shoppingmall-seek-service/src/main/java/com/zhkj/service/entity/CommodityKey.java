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
    public static final String TYPE_NAME="typeName";//商品类型

    /**
     * 商品规格详情库存价格表
     */
    public static final String TYPES_COMMODITY_SPECIFICATION_INVENTORY_PRICE="commodity_specification_inventory_price";
    public static final String COMMODITY_ID="commodityId";//商品id外键
    public static final String INVENTORY="inventory";//商品库存
    public static final String PRICE="price";//商品价格

    /**
     * 商品规格关系表
     */
    public static final String TYPES_COMMODITY_SPECIFICATION_RELATION="commodity_specification_relation";
    public static final String SPECIFICATION_NAME="specificationName";//商品规格名称
    public static final String LEVELS="levels";//商品层级
    public static final String PARENT_ID="parentId";//商品父层级id

    /**
     * 商品评价表
     */
    public static final String TYPES_COMMODITYEVALUATION="commodityevaluation";

    /**
     * 商品详细 所有图片表
     */
    public static final String TYPES_COMMODITYINTRODUCEPICTURE="commodityintroducepicture";

    /**
     * 促销types(promotionitem表)
     */
    public static final String TYPES_PROMOTIONITEM="promotionitem";
    public static final String START_TIME="startTime";//促销开始时间
    public static final String END_TIME="endTime";//促销结束时间
    public static final String DISCOUNT_PRICE="discountPrice";//促销价钱
    public static final String COMMODITY_NUMBER="commodityNumber";//促销商品数量

    /**
     * 商品类型tyeps(type表)
     */
    public static final String TYPES_TYPE="type";

    /**
     * 用户表
     */
    public static final String TYPES_USER="user";

}
