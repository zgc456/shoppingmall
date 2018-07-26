package com.zhkj.dto.seek_dto;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 20:52 2018/5/21 0021
 */
public class CommodityDTO {
    private int id;
    private String commodityName;
    private String bigPictureUrl;
    private String typeName;

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

    public String getBigPictureUrl() {
        return bigPictureUrl;
    }

    public void setBigPictureUrl(String bigPictureUrl) {
        this.bigPictureUrl = bigPictureUrl;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public CommodityDTO(int id, String commodityName, String bigPictureUrl, String typeName) {
        this.id = id;
        this.commodityName = commodityName;
        this.bigPictureUrl = bigPictureUrl;
        this.typeName = typeName;
    }

    public CommodityDTO() {
    }
}
