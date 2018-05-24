package com.zhkj.dto.seek_dto;

import java.util.Objects;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 20:52 2018/5/21 0021
 */
public class CommoditySpecificationRelationDTO {
    private int id;
    private String specificationName;
    private int levels;
    private int parentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommoditySpecificationRelationDTO that = (CommoditySpecificationRelationDTO) o;
        return id == that.id &&
                levels == that.levels &&
                parentId == that.parentId &&
                Objects.equals(specificationName, that.specificationName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, specificationName, levels, parentId);
    }
}
