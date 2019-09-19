package com.zhkj.login_service;

import com.zhkj.api.login_api.LoginApi;
import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.login_dto.UserDTO;
import com.zhkj.entity.UserEntity;
import com.zhkj.mapper.login_mapper.LoginMapper;
import com.zhkj.vo.login_vo.User_vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

/**
 * Created by haoyu on 2018/4/2.
 */
@Service
public class LoginImpl implements LoginApi {
    @Autowired
    LoginMapper loginMapper;

    /**
     * 用户登陆
     * 1.接收数据判断是否为空
     * 2.若不为空判断验证码是否正确(控制器）
     * 3.若验证码正确则查询数据库（控制器）
     * 4.若存在此用户则存入Session,跳入主页(控制器的操作)
     */
    @Override
    public UserDTO selectLogin(User_vo user_vo) {
//todo 公司老哥 业务逻辑
        UserEntity userEntity=new UserEntity();
        UserDTO userDTO = null;
        if (user_vo.getLoginName() != null && user_vo.getLogingPassword() != null) {
           userEntity=loginMapper.selectLogin(BeanMap.create(user_vo));
           if (null!=userEntity){
              userDTO=new UserDTO();
              userDTO= Conver_Type.convert(userDTO,userEntity);
           }
        }
        return userDTO;
    }
}
