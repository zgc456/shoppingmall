package com.zhkj.api.register_api;
import com.zhkj.dto.login_dto.UserDTO;

/**
 * Created by haoyu on 2018/4/2.
 * 注册API
 */
public interface RegisterApi {
    int addUser(UserDTO userEntity);
    int updateUser(int id, int authenticationId);
}
