package com.zhkj.api.commodity_api;

import com.zhkj.dto.commodity_dto.CommodityDTO;
import com.zhkj.vo.commodity_vo.CommodityVO;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface CommodityService {
    @GetMapping("/selectAll")
    List<CommodityDTO> selectAll();
    @GetMapping("/selectByTypeId")
    List<CommodityDTO> selectByTypeId(CommodityVO commodityVO);
    @GetMapping("/selectByCommodityId")
    List<CommodityDTO> selectByCommodityId(CommodityVO commodityVO);
    @GetMapping("/selectCommodity")
    List<CommodityDTO> selectCommodity();
}
