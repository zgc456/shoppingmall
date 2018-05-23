package com.zhkj.entity;

public class UserEntity {
    private int id;
    private String nickName;
    private String loginName;
    private String logingPassword;
    private String headPortraitUrl;
    private String userTypeName;
    private int authenticationId;

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

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public int getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(int authenticationId) {
        this.authenticationId = authenticationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (authenticationId != that.authenticationId) return false;
        if (nickName != null ? !nickName.equals(that.nickName) : that.nickName != null) return false;
        if (loginName != null ? !loginName.equals(that.loginName) : that.loginName != null) return false;
        if (logingPassword != null ? !logingPassword.equals(that.logingPassword) : that.logingPassword != null)
            return false;
        if (headPortraitUrl != null ? !headPortraitUrl.equals(that.headPortraitUrl) : that.headPortraitUrl != null)
            return false;
        if (userTypeName != null ? !userTypeName.equals(that.userTypeName) : that.userTypeName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (logingPassword != null ? logingPassword.hashCode() : 0);
        result = 31 * result + (headPortraitUrl != null ? headPortraitUrl.hashCode() : 0);
        result = 31 * result + (userTypeName != null ? userTypeName.hashCode() : 0);
        result = 31 * result + authenticationId;
        return result;
    }
}
