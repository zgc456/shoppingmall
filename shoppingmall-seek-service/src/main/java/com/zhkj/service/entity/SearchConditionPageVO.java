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
    private String discountIntroduce;//展示商城还未做打折的计划
    /**
     * 排序
     */
    private Integer orderDesc = 0;
    /**
     * 根据商品价钱排序
     */
    private String orderBy = "price";
    /**
     * 起始条数
     */
    private int begin;
    /**
     * 显示条数
     */
    private int size;
    /**
     * 促销开始时间
     */
    private String startDate;
    /**
     * 促销结束时间
     */
    private String endDate;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        if (size==0||size<0) {
            this.size = 10;
        }else{
            this.size=size;
        }
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
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
        if (orderDesc<0){
            this.orderDesc = 0;
        }else {
            this.orderDesc = orderDesc;
        }

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
                ", commodityPriceLTE=" + commodityPriceLTE +
                ", commodityPriceGTE=" + commodityPriceGTE +
                ", discountIntroduce='" + discountIntroduce + '\'' +
                ", orderDesc='" + orderDesc + '\'' +
                ", orderBy='" + orderBy + '\'' +
                '}';
    }
}
