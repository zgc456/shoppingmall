package com.zhkj.service.entity;

/**
 * 返回给前台对象
 */
public class CommodityTemplate {
    /**
     * 商品
     */
    private Long id;
    /**
     * 商品名称
     */
    private String commodityName;
    /**
     * 商品介绍
     */
    private String commodityIntroduce;
    /**
     * 商品图片路径
     */
    private String bigPictureUrl;
    /**
     * 商品数量
     */
    private Long commodityNumber;
    /**
     * 商品价格
     */
    private Long commodityPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBigPictureUrl() {
        return bigPictureUrl;
    }

    public void setBigPictureUrl(String bigPictureUrl) {
        this.bigPictureUrl = bigPictureUrl;
    }

    public Long getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(Long commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    public Long getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Long commodityPrice) {
        this.commodityPrice = commodityPrice;
    }
}