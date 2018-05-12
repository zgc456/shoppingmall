package com.zhkj.mapper.order_mapper;

import com.zhkj.entity.HarvestaddressEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HarvestAddressMapper {
    /**
     * 修改收货地址信息
     * @param userId 所属用户
     * @param harvestaddressEntity 收货地址信息
     * @return 是否成功
     */
    int updateHarvestAddress(@Param("userId") Integer userId, @Param("harvestaddressEntity") HarvestaddressEntity harvestaddressEntity);

    /**
     * 添加收货地址
     * @param userId 所属用户
     * @param harvestaddressEntity 收货地址信息
     * @return 是否成功
     */
    int addHarvestAddress(@Param("userId") Integer userId, @Param("harvestaddressEntity") HarvestaddressEntity harvestaddressEntity);

    /**
     * 移除收货地址
     * @param userId 所属用户
     * @param id 收货地址编号
     * @return 是否成功
     */
    int removeHarvestAddress(@Param("userId") Integer userId, @Param("id") Integer id);

    /**
     * 查询是否该用户是否已有收货地址
     * @param userId 所属用户
     * @return 是否存在
     */
    HarvestaddressEntity selectDefaultHarvestAddress(@Param("userId")Integer userId);

    /**
     * 修改用户的默认地址
     * @param userId 所属用户
     * @return 是否成功
     */
    int updateDefaultHarvestAddress(@Param("userId") Integer userId);

    /**
     * 添加收货地址为默认
     * @param userId 所属用户
     * @param addressId 收货地址编号
     * @return 是否成功
     */
    int setDefaultHarvestAddress(@Param("userId") Integer userId, @Param("addressId") Integer addressId);

    /**
     * 获取用户的所有收货地址信息
     * @param userId 用户编号
     * @return 收货地址
     */
    List<HarvestaddressEntity>  gainMyInformation(@Param("userId") Integer userId);
    /**
     * 根据用户id和地址id获取地址
     * @param userId 用户id
     * @param HarvestAddressId 地址id
     * @return 地址信息
     */
    HarvestaddressEntity selectByUserAndHarvestAddressId(@Param("userId") Integer userId,@Param("HarvestAddressId") Integer HarvestAddressId);
}
