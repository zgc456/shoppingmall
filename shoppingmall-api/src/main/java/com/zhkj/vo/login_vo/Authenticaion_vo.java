package com.zhkj.vo.login_vo;

public class Authenticaion_vo {
    private Integer id;
    private String userName;
    private String userAboutAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    private String userAddress;
    private String userPhoneNumber;
}
