package com.zhkj.mapper.login_mapper;

import com.zhkj.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by haoyu on 2018/4/2.
 * 注册
 */
@Repository
public interface RegisterMapper {
    int addUser(UserEntity userEntity);
    int updateUser(int id, int authenticationId);
}
