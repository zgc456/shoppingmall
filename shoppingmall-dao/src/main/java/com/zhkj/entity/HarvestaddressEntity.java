package com.zhkj.entity;

/**
 * Created by lenovo on 2018/4/16.
 */
public class HarvestaddressEntity {
    private int id;
    private String harvestAddressName;
    private Integer harvestIsDefault;
    private Integer typeId;
    private Integer userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHarvestAddressName() {
        return harvestAddressName;
    }

    public void setHarvestAddressName(String harvestAddressName) {
        this.harvestAddressName = harvestAddressName;
    }

    public Integer getHarvestIsDefault() {
        return harvestIsDefault;
    }

    public void setHarvestIsDefault(Integer harvestIsDefault) {
        this.harvestIsDefault = harvestIsDefault;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HarvestaddressEntity that = (HarvestaddressEntity) o;

        if (id != that.id) return false;
        if (harvestAddressName != null ? !harvestAddressName.equals(that.harvestAddressName) : that.harvestAddressName != null)
            return false;
        if (harvestIsDefault != null ? !harvestIsDefault.equals(that.harvestIsDefault) : that.harvestIsDefault != null)
            return false;
        if (typeId != null ? !typeId.equals(that.typeId) : that.typeId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (harvestAddressName != null ? harvestAddressName.hashCode() : 0);
        result = 31 * result + (harvestIsDefault != null ? harvestIsDefault.hashCode() : 0);
        result = 31 * result + (typeId != null ? typeId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
