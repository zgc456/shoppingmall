package com.zhkj.dto.seek_dto;

import java.util.Objects;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 14:29 2018/5/11 0011
 */
public class CommoditytyperelationDTO {
    private int id;
    private Integer shopprimarytypeid;
    private Integer shopminortypeid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getShopprimarytypeid() {
        return shopprimarytypeid;
    }

    public void setShopprimarytypeid(Integer shopprimarytypeid) {
        this.shopprimarytypeid = shopprimarytypeid;
    }

    public Integer getShopminortypeid() {
        return shopminortypeid;
    }

    public void setShopminortypeid(Integer shopminortypeid) {
        this.shopminortypeid = shopminortypeid;
    }
}
