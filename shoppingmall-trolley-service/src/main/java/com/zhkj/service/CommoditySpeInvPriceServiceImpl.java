package com.zhkj.service;

import com.zhkj.api.commoditySpeInvPrice_api.CommoditySpeInvPriceService;
import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.inventory_dto.CommoditySpecificationInventoryPriceDTO;
import com.zhkj.mapper.commoditySpeInvPrice_mapper.CommoditySpeInvPriceMapper;
import com.zhkj.vo.commoditySpeInvPrice_vo.CommoditySpeInvPriceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommoditySpeInvPriceServiceImpl implements CommoditySpeInvPriceService {
    @Autowired
    private CommoditySpeInvPriceMapper commoditySpeInvPriceMapper;
    @Override
    public List<CommoditySpecificationInventoryPriceDTO> queryByInvPriceId(CommoditySpeInvPriceVO commoditySpeInvPriceVO) {
        List<CommoditySpecificationInventoryPriceDTO> list=new ArrayList<>();
        return Conver_Type.convertToList(list,commoditySpeInvPriceMapper.queryByInvPriceId(BeanMap.create(commoditySpeInvPriceVO)),"com.zhkj.dto.inventory_dto.CommoditySpecificationInventoryPriceDTO");
    }
}
