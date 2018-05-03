package com.zhkj.entity;

/**
 * Created by lenovo on 2018/4/16.
 */
public class CommodityEntity {
    private int id;
    private String commodityName;
    private Integer commodityPrice;
    private String commodityIntroduce;
    private String commodityPictureUrl;
    private Integer commodityNumber;
    private Integer commodityTypeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Integer getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Integer commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public String getCommodityIntroduce() {
        return commodityIntroduce;
    }

    public void setCommodityIntroduce(String commodityIntroduce) {
        this.commodityIntroduce = commodityIntroduce;
    }

    public String getCommodityPictureUrl() {
        return commodityPictureUrl;
    }

    public void setCommodityPictureUrl(String commodityPictureUrl) {
        this.commodityPictureUrl = commodityPictureUrl;
    }

    public Integer getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(Integer commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    public Integer getCommodityTypeId() {
        return commodityTypeId;
    }

    public void setCommodityTypeId(Integer commodityTypeId) {
        this.commodityTypeId = commodityTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommodityEntity that = (CommodityEntity) o;

        if (id != that.id) return false;
        if (commodityName != null ? !commodityName.equals(that.commodityName) : that.commodityName != null)
            return false;
        if (commodityPrice != null ? !commodityPrice.equals(that.commodityPrice) : that.commodityPrice != null)
            return false;
        if (commodityIntroduce != null ? !commodityIntroduce.equals(that.commodityIntroduce) : that.commodityIntroduce != null)
            return false;
        if (commodityPictureUrl != null ? !commodityPictureUrl.equals(that.commodityPictureUrl) : that.commodityPictureUrl != null)
            return false;
        if (commodityNumber != null ? !commodityNumber.equals(that.commodityNumber) : that.commodityNumber != null)
            return false;
        if (commodityTypeId != null ? !commodityTypeId.equals(that.commodityTypeId) : that.commodityTypeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (commodityName != null ? commodityName.hashCode() : 0);
        result = 31 * result + (commodityPrice != null ? commodityPrice.hashCode() : 0);
        result = 31 * result + (commodityIntroduce != null ? commodityIntroduce.hashCode() : 0);
        result = 31 * result + (commodityPictureUrl != null ? commodityPictureUrl.hashCode() : 0);
        result = 31 * result + (commodityNumber != null ? commodityNumber.hashCode() : 0);
        result = 31 * result + (commodityTypeId != null ? commodityTypeId.hashCode() : 0);
        return result;
    }
}
