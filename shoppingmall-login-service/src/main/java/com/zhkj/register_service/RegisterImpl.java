package com.zhkj.register_service;

import com.zhkj.api.register_api.RegisterApi;
import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.login_dto.UserDTO;
import com.zhkj.entity.UserEntity;
import com.zhkj.mapper.login_mapper.RegisterMapper;
import com.zhkj.vo.login_vo.User_vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

/**
 * Created by haoyu on 2018/4/2.
 */
@Service
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
    public int addUser(User_vo user_vo) {
        int index=0;
        if(user_vo!=null){
          index= registerMapper.addUser (BeanMap.create(user_vo));
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

    @Override
    public UserDTO selectName(String name) {
        UserEntity userEntity=new UserEntity();
        UserDTO dto=null;
        try {
           userEntity = registerMapper.selectName(name);
          if (null!=userEntity)
              dto=new UserDTO();
            dto= Conver_Type.convert(dto,userEntity);
        }catch (Exception e){

        }

       return dto;
    }

}
