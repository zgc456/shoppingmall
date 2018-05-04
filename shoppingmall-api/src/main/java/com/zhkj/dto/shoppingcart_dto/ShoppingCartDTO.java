package com.zhkj.dto.shoppingcart_dto;

public class ShoppingCartDTO {
    private int id;
    private Integer commdityNumber;
    private Integer commdityPrice;
    private Integer commdityId;
    private Integer userId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCommdityNumber() {
        return commdityNumber;
    }

    public void setCommdityNumber(Integer commdityNumber) {
        this.commdityNumber = commdityNumber;
    }

    public Integer getCommdityPrice() {
        return commdityPrice;
    }

    public void setCommdityPrice(Integer commdityPrice) {
        this.commdityPrice = commdityPrice;
    }

    public Integer getCommdityId() {
        return commdityId;
    }

    public void setCommdityId(Integer commdityId) {
        this.commdityId = commdityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
