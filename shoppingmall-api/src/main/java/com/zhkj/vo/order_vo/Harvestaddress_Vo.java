package com.zhkj.vo.order_vo;


import com.zhkj.dto.order_dto.HarvestaddressEntity_Dto;

/**
 * 收货地址变动所需参数
 */
public class Harvestaddress_Vo {
    /**
     * 收货地址信息
     */
    private HarvestaddressEntity_Dto harvestAddress;

    public HarvestaddressEntity_Dto getHarvestAddress() {
        return harvestAddress;
    }

    public void setHarvestAddress(HarvestaddressEntity_Dto harvestAddress) {
        this.harvestAddress = harvestAddress;
    }
}
