package com.zhkj.api.login_api;

import com.zhkj.dto.login_dto.UserDTO;
import com.zhkj.vo.login_vo.User_vo;
import org.springframework.stereotype.Repository;

/**
 * Created by haoyu on 2018/4/2.
 * 登陆API
 */
@Repository
public interface LoginApi {
    UserDTO selectLogin(User_vo user_vo);
}
