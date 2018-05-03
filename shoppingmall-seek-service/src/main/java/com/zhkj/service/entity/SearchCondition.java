package com.zhkj.service.entity;

/**
 * 查询筛选的条件实体
 */
public class SearchCondition {
    /**
     * 商品名称
     */
    private String commodityName;
    /**
     * 商品介绍
     */
    private String commodityIntroduce;
    /**
     * 小于等于价格
     */
    private Double commodityPriceLTE;
    /**
     * 大于等于
     */
    private Double commodityPriceGTE;
    /**
     * 打折介绍
     */
    private String discountIntroduce;
    /**
     * 商品类型
     */
    private String typeNames;
    /**
     * 排序
     */
    private String orderDesc = "asc";
    /**
     * 根据商品价钱排序
     */
    private String orderBy = "commodityPrice";

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityIntroduce() {
        return commodityIntroduce;
    }

    public void setCommodityIntroduce(String commodityIntroduce) {
        this.commodityIntroduce = commodityIntroduce;
    }

    public Double getCommodityPriceLTE() {
        return commodityPriceLTE;
    }

    public void setCommodityPriceLTE(Double commodityPriceLTE) {
        this.commodityPriceLTE = commodityPriceLTE;
    }

    public Double getCommodityPriceGTE() {
        return commodityPriceGTE;
    }

    public void setCommodityPriceGTE(Double commodityPriceGTE) {
        this.commodityPriceGTE = commodityPriceGTE;
    }

    public String getDiscountIntroduce() {
        return discountIntroduce;
    }

    public void setDiscountIntroduce(String discountIntroduce) {
        this.discountIntroduce = discountIntroduce;
    }

    public String getTypeNames() {
        return typeNames;
    }

    public void setTypeNames(String typeNames) {
        this.typeNames = typeNames;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
