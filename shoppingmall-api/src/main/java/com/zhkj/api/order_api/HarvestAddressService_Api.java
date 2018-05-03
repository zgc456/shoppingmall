package com.zhkj.api.order_api;



import com.zhkj.dto.order_dto.HarvestaddressEntity_Dto;
import com.zhkj.vo.order_vo.Harvestaddress_Vo;

import java.util.List;

public interface HarvestAddressService_Api {
    /**
     * 修改收货地址
     * @param harvesaddress_vo 所需信息
     * @return 是否成功
     */
    boolean updateHarvestAddress(Harvestaddress_Vo harvesaddress_vo);

    /**
     * 添加收货地址
     * @param harvesaddress_vo 所需信息
     * @return 是否成功
     */
    boolean addHarvestAddress(Harvestaddress_Vo harvesaddress_vo);

    /**
     * 移除收货地址
     * @param harvestaddress_vo 所需信息
     * @return 是否成功
     */
    boolean removeHarvestAddress(Harvestaddress_Vo harvestaddress_vo);

    /**
     * 设置默认收货地址
     * @param harvestaddress_vo 所需参数
     * @return 是否成功
     */
    boolean setDefaultHarvestAddress(Harvestaddress_Vo harvestaddress_vo);

    /**
     * 获取我的所有收货地址信息
     * @param harvestaddress_vo 所需参数
     * @return 是否成功
     */
    List<HarvestaddressEntity_Dto>  gainMyInformation(Harvestaddress_Vo harvestaddress_vo);
}
