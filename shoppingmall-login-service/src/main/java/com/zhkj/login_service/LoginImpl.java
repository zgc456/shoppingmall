package com.zhkj.login_service;

import com.zhkj.api.login_api.LoginApi;
import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.login_dto.UserDTO;
import com.zhkj.entity.UserEntity;
import com.zhkj.mapper.login_mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haoyu on 2018/4/2.
 */
public class LoginImpl implements LoginApi {
    @Autowired
    LoginMapper loginMapper;
    /**
     * 用户登陆
     *1.接收数据判断是否为空
     *2.若不为空判断验证码是否正确(控制器）
     *3.若验证码正确则查询数据库（控制器）
     *4.若存在此用户则存入Session,跳入主页(控制器的操作)
     *5.若不存在则重新登陆并且记录失败次数存入Session
     * */
    @Override
    public List<UserDTO> selectLogin(UserDTO userEntity) {
        List<UserDTO> ls=new ArrayList<> ();
        List<UserEntity> list=new ArrayList<> ();
        UserEntity userEntity1=new com.zhkj.entity.UserEntity ();
        if(userEntity.getLoginName ()!=null&&userEntity.getLogingPassword ()!=null){
        list=loginMapper.selectLogin (Conver_Type.convert (userEntity1,userEntity));
        if(null!=list){
            ls=Conver_Type.convertToList(ls,list,"com.zhkj.dto.login_dto.UserDTO");
        }
        }
        return ls;
    }
}
