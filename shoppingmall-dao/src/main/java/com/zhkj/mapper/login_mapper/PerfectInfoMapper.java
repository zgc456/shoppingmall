package com.zhkj.mapper.login_mapper;

import com.zhkj.entity.AuthenticationEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface PerfectInfoMapper {
    int perfectInfo(Map<String, Object> map);
    AuthenticationEntity selectAuthen(Map<String, Object> map);
    int setUser(Map<String, Object> map);
}
