package com.zhkj.entity;

import java.util.Objects;

public class CommodityEntity {
    private int id;
    private String commodityName;
    private String commodityIntroduce;
    private String bigPictureUrl;
    private Integer commodityTypeRelationId;
    private CommoditytyperelationEntity commoditytyperelationEntity;
    private SpecificationsrelationEntity specificationsrelationEntity;
    private SpecificationstopicEntity specificationstopicEntity;
    private SpecificationsdetailedEntity specificationsdetailedEntity;
    private TypeEntity typeEntity;

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

    public Integer getCommodityTypeRelationId() {
        return commodityTypeRelationId;
    }

    public void setCommodityTypeRelationId(Integer commodityTypeRelationId) {
        this.commodityTypeRelationId = commodityTypeRelationId;
    }

    public CommoditytyperelationEntity getCommoditytyperelationEntity() {
        return commoditytyperelationEntity;
    }

    public void setCommoditytyperelationEntity(CommoditytyperelationEntity commoditytyperelationEntity) {
        this.commoditytyperelationEntity = commoditytyperelationEntity;
    }

    public SpecificationsrelationEntity getSpecificationsrelationEntity() {
        return specificationsrelationEntity;
    }

    public void setSpecificationsrelationEntity(SpecificationsrelationEntity specificationsrelationEntity) {
        this.specificationsrelationEntity = specificationsrelationEntity;
    }

    public SpecificationstopicEntity getSpecificationstopicEntity() {
        return specificationstopicEntity;
    }

    public void setSpecificationstopicEntity(SpecificationstopicEntity specificationstopicEntity) {
        this.specificationstopicEntity = specificationstopicEntity;
    }

    public SpecificationsdetailedEntity getSpecificationsdetailedEntity() {
        return specificationsdetailedEntity;
    }

    public void setSpecificationsdetailedEntity(SpecificationsdetailedEntity specificationsdetailedEntity) {
        this.specificationsdetailedEntity = specificationsdetailedEntity;
    }

    public TypeEntity getTypeEntity() {
        return typeEntity;
    }

    public void setTypeEntity(TypeEntity typeEntity) {
        this.typeEntity = typeEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommodityEntity that = (CommodityEntity) o;
        return id == that.id &&
                Objects.equals(commodityName, that.commodityName) &&
                Objects.equals(commodityIntroduce, that.commodityIntroduce) &&
                Objects.equals(bigPictureUrl, that.bigPictureUrl) &&
                Objects.equals(commodityTypeRelationId, that.commodityTypeRelationId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, commodityName, commodityIntroduce, bigPictureUrl, commodityTypeRelationId);
    }
}
