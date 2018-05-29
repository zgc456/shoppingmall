package com.zhkj.mapper.login_mapper;

import com.zhkj.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by haoyu on 2018/4/2.
 * 注册
 */
@Repository
public interface RegisterMapper {
    int addUser(Map<String, Object> map);
    int updateUser(int id, int authenticationId);
    UserEntity selectName(String name);

}
