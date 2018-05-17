package com.zhkj.api.specificationsrelation_api;

import com.zhkj.dto.specification_dto.SpecificationsrelationDTO;
import com.zhkj.vo.specificationRelation_vo.SpecificationRelationVO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface SpecificationRelationService {
    @GetMapping("/selectMaxByCommodityId")
    List<SpecificationsrelationDTO> selectMaxByCommodityId(SpecificationRelationVO specificationRelationVO);
}
