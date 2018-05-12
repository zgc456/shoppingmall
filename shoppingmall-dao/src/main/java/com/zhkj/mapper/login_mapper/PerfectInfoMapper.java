package com.zhkj.mapper.login_mapper;

import com.zhkj.entity.AuthenticationEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfectInfoMapper {
    int perfectInfo(AuthenticationEntity authenticationEntity, int id);
    AuthenticationEntity selectAuthen(AuthenticationEntity authenticationEntity);
}
