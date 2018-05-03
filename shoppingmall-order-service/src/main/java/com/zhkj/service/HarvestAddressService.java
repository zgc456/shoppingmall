package com.zhkj.service;


import com.zhkj.api.order_api.HarvestAddressService_Api;
import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.order_dto.HarvestaddressEntity_Dto;
import com.zhkj.entity.HarvestaddressEntity;
import com.zhkj.entity.UserEntity;

import com.zhkj.mapper.order_mapper.HarvestAddressMapper;
import com.zhkj.vo.order_vo.Harvestaddress_Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *  收货地址变动类
 *  实现接口
 *      HarvestAddressService_Api 所属 com.zhkj.api
 */
@Service
public class HarvestAddressService implements HarvestAddressService_Api {
    @Autowired
    private HarvestAddressMapper harvestAddressMapper;

    @Override
    public boolean updateHarvestAddress(Harvestaddress_Vo harvesaddress_vo) {
        boolean result = false;
        if(judgeUserIdAndAddressId(harvesaddress_vo.getHarvestAddress().getUserId(),harvesaddress_vo.getHarvestAddress().getId())){
            if(judgeUserIdAndDto(harvesaddress_vo.getHarvestAddress().getUserId(),harvesaddress_vo.getHarvestAddress())){
                HarvestaddressEntity harvestaddressEntity = new HarvestaddressEntity();
                harvestaddressEntity =Conver_Type.convert(harvestaddressEntity,harvesaddress_vo.getHarvestAddress());
                harvestAddressMapper.updateHarvestAddress(harvesaddress_vo.getHarvestAddress().getUserId(),harvestaddressEntity);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean addHarvestAddress(Harvestaddress_Vo harvesaddress_vo) {
        boolean result = false;
        if (judgeUserIdAndDto(harvesaddress_vo.getHarvestAddress().getUserId(), harvesaddress_vo.getHarvestAddress())) {
            HarvestaddressEntity harvestaddressEntity = new HarvestaddressEntity();
            harvestaddressEntity = Conver_Type.convert(harvestaddressEntity, harvesaddress_vo.getHarvestAddress());
            harvestAddressMapper.addHarvestAddress(harvesaddress_vo.getHarvestAddress().getUserId(), harvestaddressEntity);
            result = true;
        }
        return result;
    }

    @Override
    public boolean removeHarvestAddress(Harvestaddress_Vo harvesaddress_vo) {
        boolean result = false;
        if (judgeUserIdAndAddressId(harvesaddress_vo.getHarvestAddress().getUserId(), harvesaddress_vo.getHarvestAddress().getId())) {
            harvestAddressMapper.removeHarvestAddress(harvesaddress_vo.getHarvestAddress().getUserId(), harvesaddress_vo.getHarvestAddress().getId());
            result = true;
        }
        return result;
    }

    @Override
    public boolean setDefaultHarvestAddress(Harvestaddress_Vo harvesaddress_vo) {
        boolean result = false;
        if (judgeUserIdAndAddressId(harvesaddress_vo.getHarvestAddress().getUserId(), harvesaddress_vo.getHarvestAddress().getId())) {
            if(harvestAddressMapper.selectDefaultHarvestAddress(harvesaddress_vo.getHarvestAddress().getUserId()) != null){
                harvestAddressMapper.updateDefaultHarvestAddress(harvesaddress_vo.getHarvestAddress().getUserId());
                result = true;
            }else {
                harvestAddressMapper.setDefaultHarvestAddress(harvesaddress_vo.getHarvestAddress().getUserId(), harvesaddress_vo.getHarvestAddress().getId());
                result = true;
            }
        }
        return result;
    }

    @Override
    public List<HarvestaddressEntity_Dto> gainMyInformation(Harvestaddress_Vo harvesaddress_vo) {
        List<HarvestaddressEntity_Dto> listEntityDto = new ArrayList<>();
        if(harvesaddress_vo.getHarvestAddress().getUserId() > 0) {
            List<HarvestaddressEntity> listEntity = harvestAddressMapper.gainMyInformation(harvesaddress_vo.getHarvestAddress().getUserId());
            listEntityDto = Conver_Type.convertToList(listEntityDto, listEntity, "com.zhkj.dto.order_dto.HarvestaddressEntity_Dto");
        }
        return listEntityDto;
    }
    /**
     * 判断dto或userId异常
     * @param userId 所属用户
     * @param dto 收货地址信息
     * @return 是否异常
     */
    private boolean judgeUserIdAndDto(Integer userId,HarvestaddressEntity_Dto dto){
        boolean result = false;
        if(dto != null){
            if(userId != null && userId > 0){
                result = true;
            }
        }
        return result;
    }

    /**
     * 判断userId 或 addressId 是否异常
     * @param userId 所属用户
     * @param addressId 地址id
     * @return 是否异常
     */
    private boolean judgeUserIdAndAddressId(Integer userId,Integer addressId){
        boolean result = false;
        if(userId != null && userId > 0){
            if(addressId != null && addressId > 0){
                result = true;
            }
        }
        return result;
    }
}
