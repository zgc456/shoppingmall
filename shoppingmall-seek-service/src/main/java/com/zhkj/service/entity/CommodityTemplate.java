package com.zhkj.service.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * 返回给前台对象
 */
public class CommodityTemplate implements Comparable<CommodityTemplate> {
    /**
     * 商品
     */
    private Long id;
    /**
     * 商品名称
     */
    private String commodityName;
//    /**
//     * 商品图片介绍
//     */
//    private List<String> commodityPictureIntroduces;
    /**
     * 商品图片路径
     */
    private String bigPictureUrl;
    /**
     * 商品数量
     */
    private Long inventory;
    /**
     * 商品价格
     */
    private Double commodityPrice=0.0;
    /**
     * 商品类型名字
     */
    private String typeName;
    /**
     * 抢购开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 抢购结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

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

    public String getBigPictureUrl() {
        return bigPictureUrl;
    }

    public void setBigPictureUrl(String bigPictureUrl) {
        this.bigPictureUrl = bigPictureUrl;
    }

    public Long getInventory() {
        return inventory;
    }

    public void setInventory(Long inventory) {
        this.inventory = inventory;
    }

    public Double getCommodityPrice() {
        if (commodityPrice==null){
            return 0.0;
        }
        return commodityPrice;
    }

    public void setCommodityPrice(Double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    @Override
    public int compareTo(CommodityTemplate o) {
        if (this.commodityPrice>=o.getCommodityPrice()){
            return 1;
        }
        return -1;
    }
}