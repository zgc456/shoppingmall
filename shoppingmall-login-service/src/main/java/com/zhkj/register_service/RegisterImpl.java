package com.zhkj.register_service;

import com.zhkj.api.register_api.RegisterApi;

import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.login_dto.UserDTO;
import com.zhkj.entity.UserEntity;
import com.zhkj.mapper.login_mapper.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by haoyu on 2018/4/2.
 */
public class RegisterImpl implements RegisterApi {
    @Autowired
    RegisterMapper registerMapper;
    /**
     *实现注册
     *1.需要判断数据是否为空
     * 2.若不为空则发送手机验证
     * 3.判断手机验证码是否正确
     * 4.若手机验证码正确则添加用户
     * */
    @Override
    public int addUser(UserDTO userEntity) {
        int index=0;
        UserEntity userEntity1=new UserEntity ();
        if(userEntity!=null){
          index= registerMapper.addUser (Conver_Type.convert (userEntity1,userEntity));
        }
        return index;
    }
/*
* 修改user表中的完善信息表Id
* */
    @Override
    public int updateUser(int id, int authenticationId) {
       int index=0;
        if(id!=0&&authenticationId!=0){
            index=registerMapper.updateUser(id,authenticationId);
        }
        return index;
    }
}
