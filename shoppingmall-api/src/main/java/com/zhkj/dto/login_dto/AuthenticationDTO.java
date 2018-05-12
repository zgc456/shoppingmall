package com.zhkj.dto.login_dto;

public class AuthenticationDTO {
    private int id;
    private String userName;
    private String userAboutAddress;
    private String userAddress;
    private String userPhoneNumber;
    private Integer userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAboutAddress() {
        return userAboutAddress;
    }

    public void setUserAboutAddress(String userAboutAddress) {
        this.userAboutAddress = userAboutAddress;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
