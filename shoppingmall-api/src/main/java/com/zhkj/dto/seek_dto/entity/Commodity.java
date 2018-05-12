package com.zhkj.dto.seek_dto.entity;

import java.util.Objects;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 16:13 2018/5/12 0012
 */
public class Commodity {
    private int id;
    private String commodityName;
    private String commodityIntroduce;
    private String bigPictureUrl;
    private Integer commodityTypeRelationId;

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

}
