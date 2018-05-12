package com.zhkj.dto.seek_dto.entity;

import java.util.Objects;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 16:13 2018/5/12 0012
 */
public class Commoditytyperelation {
    private int id;
    private Integer shopPrimaryTypeId;
    private Integer shopMinorTypeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getShopPrimaryTypeId() {
        return shopPrimaryTypeId;
    }

    public void setShopPrimaryTypeId(Integer shopPrimaryTypeId) {
        this.shopPrimaryTypeId = shopPrimaryTypeId;
    }

    public Integer getShopMinorTypeId() {
        return shopMinorTypeId;
    }

    public void setShopMinorTypeId(Integer shopMinorTypeId) {
        this.shopMinorTypeId = shopMinorTypeId;
    }

}
