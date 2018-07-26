package com.zhkj.service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description: 商品规格对象
 * @Date: Created in 16:46 2018/7/19 0019
 */
public class CommoditySpecificationDTO {
    private long id;//商品规格id
    private long commodityId;//商品id
    //商品规格：例如：颜色：红、白，尺寸：X、M
    private CommoditySpecificationTypeDTO commoditySpecificationType1;
    private CommoditySpecificationTypeDTO commoditySpecificationType2;
    private CommoditySpecificationTypeDTO commoditySpecificationType3;
    private CommoditySpecificationTypeDTO commoditySpecificationType4;
    //商品规格1
    private String specification1;
    //商品规格2
    private String specification2;
    //商品规格3
    private String specification3;
    //商品规格4
    private String specification4;
    private long inventory;//库存
    private double price;//价格
    private String picture;//小图片
    private long startTime;
    private long endTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    public long getId() {
        return id;
    }

    public CommoditySpecificationTypeDTO getCommoditySpecificationType1() {
        return commoditySpecificationType1;
    }

    public void setCommoditySpecificationType1(CommoditySpecificationTypeDTO commoditySpecificationType1) {
        this.commoditySpecificationType1 = commoditySpecificationType1;
    }

    public CommoditySpecificationTypeDTO getCommoditySpecificationType2() {
        return commoditySpecificationType2;
    }

    public void setCommoditySpecificationType2(CommoditySpecificationTypeDTO commoditySpecificationType2) {
        this.commoditySpecificationType2 = commoditySpecificationType2;
    }

    public CommoditySpecificationTypeDTO getCommoditySpecificationType3() {
        return commoditySpecificationType3;
    }

    public void setCommoditySpecificationType3(CommoditySpecificationTypeDTO commoditySpecificationType3) {
        this.commoditySpecificationType3 = commoditySpecificationType3;
    }

    public CommoditySpecificationTypeDTO getCommoditySpecificationType4() {
        return commoditySpecificationType4;
    }

    public void setCommoditySpecificationType4(CommoditySpecificationTypeDTO commoditySpecificationType4) {
        this.commoditySpecificationType4 = commoditySpecificationType4;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(long commodityId) {
        this.commodityId = commodityId;
    }

    public long getInventory() {
        return inventory;
    }

    public void setInventory(long inventory) {
        this.inventory = inventory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSpecification1() {
        return specification1;
    }

    public void setSpecification1(String specification1) {
        this.specification1 = specification1;
    }

    public String getSpecification2() {
        return specification2;
    }

    public void setSpecification2(String specification2) {
        this.specification2 = specification2;
    }

    public String getSpecification3() {
        return specification3;
    }

    public void setSpecification3(String specification3) {
        this.specification3 = specification3;
    }

    public String getSpecification4() {
        return specification4;
    }

    public void setSpecification4(String specification4) {
        this.specification4 = specification4;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
