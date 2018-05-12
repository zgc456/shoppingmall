package com.zhkj.register_service;

import com.zhkj.api.register_api.PerfectInfoAPI;

import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.login_dto.AuthenticationDTO;
import com.zhkj.entity.AuthenticationEntity;
import com.zhkj.mapper.login_mapper.PerfectInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class PerfectInfoImpl implements PerfectInfoAPI {
    @Autowired
    PerfectInfoMapper perfectInfoMapper;
    @Autowired
    RegisterImpl register;
    /*
    * 完善用户信息表，修改用户表中的信息表id
    * */
    @Override
    public int perfectInfo(AuthenticationDTO authenticationEntity, int id) {
        int index=0;
        AuthenticationEntity au=new AuthenticationEntity();
        //如果添加成功则修改user表中的完善信息外键
        if(authenticationEntity!=null){
           index= perfectInfoMapper.perfectInfo(Conver_Type.convert(au,authenticationEntity),id);
        }
          if (index>0){
              int authenticationId=selectAuthen(authenticationEntity).getId();//获取外键后修改user表
              register.updateUser(id,authenticationId);
          }
        return index;
    }
   /*
   * 查询出本次完善信息的具体数据,用于获取ID修改user表
   * */
    @Override
    public AuthenticationDTO selectAuthen(AuthenticationDTO authenticationEntity) {
         AuthenticationEntity authenticationEntity1=new AuthenticationEntity();
         AuthenticationDTO au=new AuthenticationDTO();
         if(authenticationEntity!=null){
            authenticationEntity1=perfectInfoMapper.selectAuthen(Conver_Type.convert(authenticationEntity1,authenticationEntity));
         }
         au=Conver_Type.convert(au,authenticationEntity1);//到此返回的是一个dto的实体
        return au;
    }
}
