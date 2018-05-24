package com.zhkj.service.entity;

import com.zhkj.dto.seek_dto.CommoditySpecificationInventoryPriceDTO;
import com.zhkj.dto.seek_dto.CommoditySpecificationRelationDTO;
import com.zhkj.dto.seek_dto.PromotionitemDTO;

import java.util.List;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description: 商品详细展示类提供给前台数据展示
 * @Date: Created in 14:08 2018/5/23 0023
 */
public class CommodityDetailsDTO {
    private String id;//商品id
    private String commodityName;//商品名字
    //商品所有规格集合
    private List<CommoditySpecificationInventoryPriceDTO> commoditySpecificationInventoryPriceDTOS;
    //商品所有规格的父规格
    private List<CommoditySpecificationRelationDTO> commoditySpecificationRelationDTOS;
    private List<String> headPictures;//商品头部图片
    private List<String> detailsPictures;//商品详情图片
    private PromotionitemDTO promotionitemDTO;//商品抢购对象
    private int inventory;//库存
    private Double price;//商品价格

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public List<CommoditySpecificationInventoryPriceDTO> getCommoditySpecificationInventoryPriceDTOS() {
        return commoditySpecificationInventoryPriceDTOS;
    }

    public void setCommoditySpecificationInventoryPriceDTOS(List<CommoditySpecificationInventoryPriceDTO> commoditySpecificationInventoryPriceDTOS) {
        this.commoditySpecificationInventoryPriceDTOS = commoditySpecificationInventoryPriceDTOS;
    }

    public List<CommoditySpecificationRelationDTO> getCommoditySpecificationRelationDTOS() {
        return commoditySpecificationRelationDTOS;
    }

    public void setCommoditySpecificationRelationDTOS(List<CommoditySpecificationRelationDTO> commoditySpecificationRelationDTOS) {
        this.commoditySpecificationRelationDTOS = commoditySpecificationRelationDTOS;
    }

    public List<String> getHeadPictures() {
        return headPictures;
    }

    public void setHeadPictures(List<String> headPictures) {
        this.headPictures = headPictures;
    }

    public List<String> getDetailsPictures() {
        return detailsPictures;
    }

    public void setDetailsPictures(List<String> detailsPictures) {
        this.detailsPictures = detailsPictures;
    }

    public PromotionitemDTO getPromotionitemDTO() {
        return promotionitemDTO;
    }

    public void setPromotionitemDTO(PromotionitemDTO promotionitemDTO) {
        this.promotionitemDTO = promotionitemDTO;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
