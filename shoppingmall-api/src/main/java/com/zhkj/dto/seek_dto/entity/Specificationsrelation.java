package com.zhkj.dto.seek_dto.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 16:13 2018/5/12 0012
 */
public class Specificationsrelation {
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

}
