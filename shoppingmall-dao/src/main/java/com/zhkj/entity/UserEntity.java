package com.zhkj.entity;

/**
 * Created by lenovo on 2018/4/16.
 */
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

        if (id != that.id) return false;
        if (nickName != null ? !nickName.equals(that.nickName) : that.nickName != null) return false;
        if (loginName != null ? !loginName.equals(that.loginName) : that.loginName != null) return false;
        if (logingPassword != null ? !logingPassword.equals(that.logingPassword) : that.logingPassword != null)
            return false;
        if (headPortraitUrl != null ? !headPortraitUrl.equals(that.headPortraitUrl) : that.headPortraitUrl != null)
            return false;
        if (userTypeId != null ? !userTypeId.equals(that.userTypeId) : that.userTypeId != null) return false;
        if (authenticationId != null ? !authenticationId.equals(that.authenticationId) : that.authenticationId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (logingPassword != null ? logingPassword.hashCode() : 0);
        result = 31 * result + (headPortraitUrl != null ? headPortraitUrl.hashCode() : 0);
        result = 31 * result + (userTypeId != null ? userTypeId.hashCode() : 0);
        result = 31 * result + (authenticationId != null ? authenticationId.hashCode() : 0);
        return result;
    }
}
