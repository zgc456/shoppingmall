package com.zhkj.api.register_api;
import com.zhkj.dto.login_dto.UserDTO;
import com.zhkj.vo.login_vo.User_vo;

/**
 * Created by haoyu on 2018/4/2.
 * 注册API
 */
public interface RegisterApi {
    int addUser(User_vo user_vo);
    int updateUser(int id, int authenticationId);
    UserDTO selectName(String name);
}
