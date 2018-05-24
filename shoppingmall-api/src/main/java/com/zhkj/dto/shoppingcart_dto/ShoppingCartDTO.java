package com.zhkj.dto.shoppingcart_dto;

import com.zhkj.dto.inventory_dto.CommoditySpecificationInventoryPriceDTO;

public class ShoppingCartDTO {
    private int id;
    private Integer commodityNumber;
    private Integer commodityId;
    private String commodityName;
    private Integer commoditySipId;
    private Integer userId;
    private Double commodityPrice;
    private CommoditySpecificationInventoryPriceDTO commoditySpecificationInventoryPriceDTO;

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

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Integer getCommoditySipId() {
        return commoditySipId;
    }

    public void setCommoditySipId(Integer commoditySipId) {
        this.commoditySipId = commoditySipId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public CommoditySpecificationInventoryPriceDTO getCommoditySpecificationInventoryPriceDTO() {
        return commoditySpecificationInventoryPriceDTO;
    }

    public void setCommoditySpecificationInventoryPriceDTO(CommoditySpecificationInventoryPriceDTO commoditySpecificationInventoryPriceDTO) {
        this.commoditySpecificationInventoryPriceDTO = commoditySpecificationInventoryPriceDTO;
    }
}
