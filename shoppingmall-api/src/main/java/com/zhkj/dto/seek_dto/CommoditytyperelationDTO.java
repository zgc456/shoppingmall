package com.zhkj.dto.seek_dto;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 14:29 2018/5/11 0011
 */
public class CommoditytyperelationDTO {
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
