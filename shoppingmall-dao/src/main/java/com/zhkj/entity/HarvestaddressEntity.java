package com.zhkj.entity;

import java.util.Objects;

public class HarvestaddressEntity {
    private int id;
    private String harvestAddressName;
    private Integer harvestIsDefault;
    private Integer typeId;
    private Integer userId;
    private String userName;
    private String userPhone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

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
        return id == that.id &&
                Objects.equals(harvestAddressName, that.harvestAddressName) &&
                Objects.equals(harvestIsDefault, that.harvestIsDefault) &&
                Objects.equals(typeId, that.typeId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, harvestAddressName, harvestIsDefault, typeId, userId);
    }
}
