package com.zhkj.entity;

import java.util.Objects;

public class UserEntity {
    private int id;
    private String nickName;
    private String loginName;
    private String logingPassword;
    private String headPortraitUrl;
    private Integer userTypeId;
    private Integer authenticationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLogingPassword() {
        return logingPassword;
    }

    public void setLogingPassword(String logingPassword) {
        this.logingPassword = logingPassword;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Integer getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(Integer authenticationId) {
        this.authenticationId = authenticationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                Objects.equals(nickName, that.nickName) &&
                Objects.equals(loginName, that.loginName) &&
                Objects.equals(logingPassword, that.logingPassword) &&
                Objects.equals(headPortraitUrl, that.headPortraitUrl) &&
                Objects.equals(userTypeId, that.userTypeId) &&
                Objects.equals(authenticationId, that.authenticationId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nickName, loginName, logingPassword, headPortraitUrl, userTypeId, authenticationId);
    }
}
