package com.zhkj.service.entity;

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
    private long inventory;//库存
    private double price;//价格
    private String picture;//小图片

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
}
