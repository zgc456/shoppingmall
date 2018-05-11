package com.zhkj.dto.specification_dto;

import java.math.BigDecimal;

public class SpecificationsrelationDTO {
    private Integer id;
    private Integer commodityNumber;
    private Double commodityPrice;
    private Integer commodityId;
    private String smallPictureUrl;
    private Integer typeId;
    private Integer speciTopicId;
    private Integer speciDetailedId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(Integer commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    public Double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Double commodityPrice) {
        this.commodityPrice = commodityPrice;
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

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
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
}
