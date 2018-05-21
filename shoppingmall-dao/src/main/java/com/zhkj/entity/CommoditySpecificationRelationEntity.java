package com.zhkj.entity;

public class CommoditySpecificationRelationEntity {
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

        CommoditySpecificationRelationEntity that = (CommoditySpecificationRelationEntity) o;

        if (id != that.id) return false;
        if (levels != that.levels) return false;
        if (parentId != that.parentId) return false;
        if (specificationName != null ? !specificationName.equals(that.specificationName) : that.specificationName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (specificationName != null ? specificationName.hashCode() : 0);
        result = 31 * result + levels;
        result = 31 * result + parentId;
        return result;
    }
}
