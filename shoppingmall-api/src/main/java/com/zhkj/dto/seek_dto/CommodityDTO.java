package com.zhkj.dto.seek_dto;

import java.util.Objects;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 14:29 2018/5/11 0011
 */
public class CommodityDTO {
    private int id;
    private String commodityname;
    private String commodityintroduce;
    private String bigpictureurl;
    private Integer commoditytyperelationid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommodityname() {
        return commodityname;
    }

    public void setCommodityname(String commodityname) {
        this.commodityname = commodityname;
    }

    public String getCommodityintroduce() {
        return commodityintroduce;
    }

    public void setCommodityintroduce(String commodityintroduce) {
        this.commodityintroduce = commodityintroduce;
    }

    public String getBigpictureurl() {
        return bigpictureurl;
    }

    public void setBigpictureurl(String bigpictureurl) {
        this.bigpictureurl = bigpictureurl;
    }

    public Integer getCommoditytyperelationid() {
        return commoditytyperelationid;
    }

    public void setCommoditytyperelationid(Integer commoditytyperelationid) {
        this.commoditytyperelationid = commoditytyperelationid;
    }
}
