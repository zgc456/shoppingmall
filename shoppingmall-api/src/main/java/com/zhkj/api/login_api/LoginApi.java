package com.zhkj.api.login_api;

import com.zhkj.dto.login_dto.UserDTO;

import java.util.List;

/**
 * Created by haoyu on 2018/4/2.
 * 登陆API
 */
public interface LoginApi {
    List<UserDTO> selectLogin(UserDTO userEntity);
}
