package com.zhkj.dto.seek_dto;

import java.util.Objects;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 20:52 2018/5/21 0021
 */
public class UserDTO {
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
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id &&
                authenticationId == userDTO.authenticationId &&
                Objects.equals(nickName, userDTO.nickName) &&
                Objects.equals(loginName, userDTO.loginName) &&
                Objects.equals(logingPassword, userDTO.logingPassword) &&
                Objects.equals(headPortraitUrl, userDTO.headPortraitUrl) &&
                Objects.equals(userTypeName, userDTO.userTypeName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, nickName, loginName, logingPassword, headPortraitUrl, userTypeName, authenticationId);
    }
}
