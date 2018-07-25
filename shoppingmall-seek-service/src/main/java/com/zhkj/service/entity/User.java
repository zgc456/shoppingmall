package com.zhkj.service.entity;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description: 商城用户对象
 * @Date: Created in 19:29 2018/7/23 0023
 */
public class User {
    private long id;
    private String nickName;
    private String loginName;
    private String logingPassword;
    private String headPortraitUrl;
    private String userTypeName;
    private long authenticationId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(long authenticationId) {
        this.authenticationId = authenticationId;
    }
}
