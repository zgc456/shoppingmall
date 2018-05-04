package com.zhkj.entity;

import java.util.Objects;

public class AuthenticationEntity {
    private int id;
    private String userName;
    private String userAboutAddress;
    private String userAddress;
    private String userPhoneNumber;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenticationEntity that = (AuthenticationEntity) o;
        return id == that.id &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(userAboutAddress, that.userAboutAddress) &&
                Objects.equals(userAddress, that.userAddress) &&
                Objects.equals(userPhoneNumber, that.userPhoneNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userName, userAboutAddress, userAddress, userPhoneNumber);
    }
}
