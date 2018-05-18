package com.zhkj.service;

import com.zhkj.api.specificationsrelation_api.SpecificationRelationService;
import com.zhkj.copy_properties.Conver_Type;
import com.zhkj.dto.specification_dto.SpecificationsrelationDTO;
import com.zhkj.mapper.specificationsrelation_mapper.SpecificationsRelationMapper;
import com.zhkj.vo.specificationRelation_vo.SpecificationRelationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecificationRelationServiceImpl implements SpecificationRelationService
{
    @Autowired
    private SpecificationsRelationMapper specificationsRelationMapper;
    @Override
    public List<SpecificationsrelationDTO> selectMaxByCommodityId(SpecificationRelationVO specificationRelationVO) {
        List<SpecificationsrelationDTO> list=new ArrayList<>();
        return Conver_Type.convertToList(list,specificationsRelationMapper.selectMaxByCommodityId(BeanMap.create(specificationRelationVO)),"com.zhkj.dto.specification_dto.SpecificationsrelationDTO");
    }
}
