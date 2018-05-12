package com.zhkj.api.register_api;

import com.zhkj.dto.login_dto.AuthenticationDTO;

/**
 * 用户信息完善类
 * */
public interface PerfectInfoAPI {
    int perfectInfo(AuthenticationDTO authenticationEntity, int id);
    AuthenticationDTO selectAuthen(AuthenticationDTO authenticationEntity);
}
