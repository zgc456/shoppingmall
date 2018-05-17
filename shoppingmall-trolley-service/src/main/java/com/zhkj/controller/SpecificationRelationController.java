package com.zhkj.controller;

import com.zhkj.dto.specification_dto.SpecificationsrelationDTO;
import com.zhkj.service.SpecificationRelationServiceImpl;
import com.zhkj.vo.specificationRelation_vo.SpecificationRelationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class SpecificationRelationController {
    @Autowired
    private SpecificationRelationServiceImpl specificationRelationService;
    @GetMapping("/selectMaxByCommodityId")
    public List<SpecificationsrelationDTO> selectMaxByCommodityId(@RequestParam("commodityId") Integer commodityId){
        SpecificationRelationVO specificationRelationVO=new SpecificationRelationVO();
        specificationRelationVO.setCommodityId(commodityId);
       return specificationRelationService.selectMaxByCommodityId(specificationRelationVO);
    }
}
