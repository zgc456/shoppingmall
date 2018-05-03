package com.zhkj.entity;

import java.math.BigDecimal;

/**
 * Created by lenovo on 2018/4/21.
 */
public class SpecificationsrelationEntity {
    private int id;
    private Integer commodityNumber;
    private BigDecimal commodityPrice;
    private Integer commodityId;
    private String smallPictureUrl;
    private Integer typeId;
    private Integer speciTopicId;
    private Integer speciDetailedId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(Integer commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    public BigDecimal getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(BigDecimal commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getSmallPictureUrl() {
        return smallPictureUrl;
    }

    public void setSmallPictureUrl(String smallPictureUrl) {
        this.smallPictureUrl = smallPictureUrl;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getSpeciTopicId() {
        return speciTopicId;
    }

    public void setSpeciTopicId(Integer speciTopicId) {
        this.speciTopicId = speciTopicId;
    }

    public Integer getSpeciDetailedId() {
        return speciDetailedId;
    }

    public void setSpeciDetailedId(Integer speciDetailedId) {
        this.speciDetailedId = speciDetailedId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecificationsrelationEntity that = (SpecificationsrelationEntity) o;

        if (id != that.id) return false;
        if (commodityNumber != null ? !commodityNumber.equals(that.commodityNumber) : that.commodityNumber != null)
            return false;
        if (commodityPrice != null ? !commodityPrice.equals(that.commodityPrice) : that.commodityPrice != null)
            return false;
        if (commodityId != null ? !commodityId.equals(that.commodityId) : that.commodityId != null) return false;
        if (smallPictureUrl != null ? !smallPictureUrl.equals(that.smallPictureUrl) : that.smallPictureUrl != null)
            return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;
        if (speciTopicId != null ? !speciTopicId.equals(that.speciTopicId) : that.speciTopicId != null) return false;
        if (speciDetailedId != null ? !speciDetailedId.equals(that.speciDetailedId) : that.speciDetailedId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (commodityNumber != null ? commodityNumber.hashCode() : 0);
        result = 31 * result + (commodityPrice != null ? commodityPrice.hashCode() : 0);
        result = 31 * result + (commodityId != null ? commodityId.hashCode() : 0);
        result = 31 * result + (smallPictureUrl != null ? smallPictureUrl.hashCode() : 0);
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (speciTopicId != null ? speciTopicId.hashCode() : 0);
        result = 31 * result + (speciDetailedId != null ? speciDetailedId.hashCode() : 0);
        return result;
    }
}
