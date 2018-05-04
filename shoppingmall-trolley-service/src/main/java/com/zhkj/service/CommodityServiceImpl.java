package com.zhkj.service;

import com.zhkj.api.commodity_api.CommodityService;
import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.commodity_dto.CommodityDTO;
import com.zhkj.mapper.commodity_mapper.CommodityMapper;
import com.zhkj.vo.commodity_vo.CommodityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityMapper commodityMapper;
    @Override
    public List selectAll() {
//        List<CommodityDTO> list=new ArrayList<>();
        //return Conver_Type.convertToList(list,commodityMapper.selectAll(),"com.zhkj.dto.commodity_dto.CommodityDTO");
        return commodityMapper.selectAll();
    }

    @Override
    public List selectByTypeId(CommodityVO commodityVO) {
        return commodityMapper.selectByTypeId(BeanMap.create(commodityVO));
    }

    @Override
    public List<CommodityDTO> selectByCommodityId(CommodityVO commodityVO) {
        return commodityMapper.selectByCommodityId(BeanMap.create(commodityVO));
    }

    @Override
    public List<CommodityDTO> selectCommodity() {
        List<CommodityDTO> list=new ArrayList<>();
        return Conver_Type.convertToList(list,commodityMapper.selectCommodity(),"com.zhkj.dto.commodity_dto.CommodityDTO") ;
    }
}
