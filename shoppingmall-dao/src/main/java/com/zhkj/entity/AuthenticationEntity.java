package com.zhkj.entity;

/**
 * Created by lenovo on 2018/4/16.
 */
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

        if (id != that.id) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userAboutAddress != null ? !userAboutAddress.equals(that.userAboutAddress) : that.userAboutAddress != null)
            return false;
        if (userAddress != null ? !userAddress.equals(that.userAddress) : that.userAddress != null) return false;
        if (userPhoneNumber != null ? !userPhoneNumber.equals(that.userPhoneNumber) : that.userPhoneNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userAboutAddress != null ? userAboutAddress.hashCode() : 0);
        result = 31 * result + (userAddress != null ? userAddress.hashCode() : 0);
        result = 31 * result + (userPhoneNumber != null ? userPhoneNumber.hashCode() : 0);
        return result;
    }
}
