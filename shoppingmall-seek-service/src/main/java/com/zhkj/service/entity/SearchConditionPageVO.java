package com.zhkj.service.entity;

/**
 * 查询筛选的条件实体
 */
public class SearchConditionPageVO {
    /**
     * 商品id
     */
    private String id;
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
     * 排序
     */
    private Integer orderDesc = 0;
    /**
     * 根据商品价钱排序
     */
    private String orderBy = "commodityprice";
    /**
     * 启示条数
     */
    private int from=0;
    /**
     * 显示条数
     */
    private int size=0;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

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

    public Integer getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(Integer orderDesc) {
        this.orderDesc = orderDesc;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SearchConditionPageVO{" +
                "id='" + id + '\'' +
                ", commodityName='" + commodityName + '\'' +
                ", commodityIntroduce='" + commodityIntroduce + '\'' +
                ", commodityPriceLTE=" + commodityPriceLTE +
                ", commodityPriceGTE=" + commodityPriceGTE +
                ", discountIntroduce='" + discountIntroduce + '\'' +
                ", orderDesc='" + orderDesc + '\'' +
                ", orderBy='" + orderBy + '\'' +
                '}';
    }
}
