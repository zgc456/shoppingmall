package com.zhkj.service.entity;

import java.util.List;

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
    private String commodityPriceLTE;
    /**
     * 大于等于
     */
    private String commodityPriceGTE;
    /**
     * 打折介绍
     */
    private String discountIntroduce;
    /**
     * 商品类型
     */
    private List<String> typeNames;
    /**
     * 排序
     */
    private String orderDesc="asc";
    /**
     * 根据商品价钱排序
     */
    private String orderBy="commodityPrice";

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

    public String getCommodityPriceLTE() {
        return commodityPriceLTE;
    }

    public void setCommodityPriceLTE(String commodityPriceLTE) {
        this.commodityPriceLTE = commodityPriceLTE;
    }

    public String getCommodityPriceGTE() {
        return commodityPriceGTE;
    }

    public void setCommodityPriceGTE(String commodityPriceGTE) {
        this.commodityPriceGTE = commodityPriceGTE;
    }

    public String getDiscountIntroduce() {
        return discountIntroduce;
    }

    public void setDiscountIntroduce(String discountIntroduce) {
        this.discountIntroduce = discountIntroduce;
    }

    public List<String> getTypeNames() {
        return typeNames;
    }

    public void setTypeNames(List<String> typeNames) {
        this.typeNames = typeNames;
    }
}
