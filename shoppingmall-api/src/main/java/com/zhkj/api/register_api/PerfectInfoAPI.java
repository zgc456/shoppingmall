package com.zhkj.api.register_api;

import com.zhkj.dto.login_dto.AuthenticationDTO;
import com.zhkj.vo.login_vo.Authenticaion_vo;

/**
 * 用户信息完善类
 * */
public interface PerfectInfoAPI {
    int perfectInfo(Authenticaion_vo authentication_vo, int id);
    AuthenticationDTO selectAuthen(Authenticaion_vo authentication_vo);
    int setUser(Authenticaion_vo au);
}
