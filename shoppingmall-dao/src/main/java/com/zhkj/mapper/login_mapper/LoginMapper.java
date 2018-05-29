package com.zhkj.mapper.login_mapper;

import com.zhkj.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by haoyu on 2018/4/2.
 * 登陆查询
 */
@Repository
public interface LoginMapper {
    UserEntity selectLogin(Map<String, Object> map);
}
