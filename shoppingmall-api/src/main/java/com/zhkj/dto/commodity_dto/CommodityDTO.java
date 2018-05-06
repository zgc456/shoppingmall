package com.zhkj.dto.commodity_dto;

import com.zhkj.dto.specification_dto.SpecificationsdetailedDTO;
import com.zhkj.dto.specification_dto.SpecificationsrelationDTO;
import com.zhkj.dto.specification_dto.SpecificationstopicDTO;
import com.zhkj.dto.type_dto.TypeDTO;

public class CommodityDTO {
    private int id;
    private String commodityName;
    private String commodityIntroduce;
    private String bigPictureUrl;
    private Integer commodityTypeRelationId;
    private CommoditytyperelationDTO commoditytyperelationDTO;
    private SpecificationsrelationDTO specificationsrelationDTO;
    private SpecificationstopicDTO specificationstopicDTO;
    private SpecificationsdetailedDTO specificationsdetailedDTO;
    private TypeDTO typeDTO;
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

    public CommoditytyperelationDTO getCommoditytyperelationDTO() {
        return commoditytyperelationDTO;
    }

    public void setCommoditytyperelationDTO(CommoditytyperelationDTO commoditytyperelationDTO) {
        this.commoditytyperelationDTO = commoditytyperelationDTO;
    }

    public SpecificationsrelationDTO getSpecificationsrelationDTO() {
        return specificationsrelationDTO;
    }

    public void setSpecificationsrelationDTO(SpecificationsrelationDTO specificationsrelationDTO) {
        this.specificationsrelationDTO = specificationsrelationDTO;
    }

    public SpecificationstopicDTO getSpecificationstopicDTO() {
        return specificationstopicDTO;
    }

    public void setSpecificationstopicDTO(SpecificationstopicDTO specificationstopicDTO) {
        this.specificationstopicDTO = specificationstopicDTO;
    }

    public SpecificationsdetailedDTO getSpecificationsdetailedDTO() {
        return specificationsdetailedDTO;
    }

    public void setSpecificationsdetailedDTO(SpecificationsdetailedDTO specificationsdetailedDTO) {
        this.specificationsdetailedDTO = specificationsdetailedDTO;
    }

    public TypeDTO getTypeDTO() {
        return typeDTO;
    }

    public void setTypeDTO(TypeDTO typeDTO) {
        this.typeDTO = typeDTO;
    }
}
