package com.zhkj.vo.order_vo;


import com.zhkj.dto.order_dto.HarvestaddressEntity_Dto;

/**
 * 收货地址变动所需参数
 */
public class Harvestaddress_Vo {
    public Harvestaddress_Vo(){}

    public Harvestaddress_Vo(int id,String harvestAddressName,Integer harvestIsDefault,Integer typeId,Integer userId,String userName,String userPhone){
        this.id=id;
        this.harvestAddressName=harvestAddressName;
        this.harvestIsDefault=harvestIsDefault;
        this.typeId=typeId;
        this.userId=userId;
        this.userName=userName;
        this.userPhone=userPhone;
    }


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

}
