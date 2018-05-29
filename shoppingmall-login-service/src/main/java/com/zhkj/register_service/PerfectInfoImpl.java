package com.zhkj.register_service;

import com.alibaba.fastjson.JSONArray;
import com.zhkj.api.register_api.PerfectInfoAPI;
import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.login_dto.AuthenticationDTO;
import com.zhkj.dto.login_dto.UserDTO;
import com.zhkj.entity.AuthenticationEntity;
import com.zhkj.mapper.login_mapper.PerfectInfoMapper;
import com.zhkj.vo.login_vo.Authenticaion_vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PerfectInfoImpl implements PerfectInfoAPI {
    @Autowired
    PerfectInfoMapper perfectInfoMapper;
    @Autowired
    RegisterImpl register;
    @Autowired
    StringRedisTemplate redisTemplate;

    /*
     * 完善用户信息表，修改用户表中的信息表id
     * 首先判断此用户是否已经完善过信息
     * 如果没有则添加
     * 若手机号存在则判断此用户是否对应此手机号
     * 若对应则修改，相反则返回信息:手机号已经被绑定
     * */
    @Transactional
    @Override
    public int perfectInfo(Authenticaion_vo authenticaion_vo, int id) {
        int index = 0;
        AuthenticationDTO authenticationDTO = null;
        try {
            if (authenticaion_vo != null) {
                if (selectAuthen(authenticaion_vo).getId()!=0) {
                    authenticationDTO = selectAuthen(authenticaion_vo);
                }//查询信息是否完善
            }
            if (authenticationDTO == null) {
                if (authenticaion_vo != null) {
                    index = perfectInfoMapper.perfectInfo(BeanMap.create(authenticaion_vo));
                }
                //如果添加成功则修改user表中的完善信息外键
                if (index > 0) {
                    int authenticationId = selectAuthen(authenticaion_vo).getId();//获取外键后修改user表
                    index = register.updateUser(id, authenticationId);
                }
            } else {
                //判断此用户是否对应此手机号,若对应则修改
                int keyid = authenticationDTO.getId();//获取完善信息的id,用于比较user的外键
                String user_if = redisTemplate.opsForValue().get("user_info");
                List<UserDTO> userDTO = JSONArray.parseArray(user_if, UserDTO.class);
                int userid = 0;
                userid = userDTO.get(0).getAuthenticationId();
                if (keyid == userid) {
                    //修改信息表
                    authenticaion_vo.setId(keyid);
                    index = setUser(authenticaion_vo);
                } else {
                    index = -1;//手机号被绑定
                }
            }
        } catch (Exception e) {
        }
        return index;
    }

    /*
     * 查询出本次完善信息的具体数据,用于获取ID修改user表
     * 数据若为Null则添加，相反则判断
     * */
    @Override
    public AuthenticationDTO selectAuthen(Authenticaion_vo authenticaion_vo) {
        AuthenticationEntity authenticationEntity1 = new AuthenticationEntity();
        AuthenticationDTO au = new AuthenticationDTO();
        try {
            if (authenticaion_vo != null) {
                authenticationEntity1 = perfectInfoMapper.selectAuthen(BeanMap.create(authenticaion_vo));
                if (authenticationEntity1 != null) {
                    au = Conver_Type.convert(au, authenticationEntity1);//到此返回的是一个dto的实体
                }
            }
        } catch (Exception e) {

        }
        return au;
    }

    @Override
    public int setUser(Authenticaion_vo au) {
        return perfectInfoMapper.setUser(BeanMap.create(au));
    }
}
