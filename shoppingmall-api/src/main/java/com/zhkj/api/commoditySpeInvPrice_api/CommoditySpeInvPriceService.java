package com.zhkj.api.commoditySpeInvPrice_api;

import com.zhkj.dto.inventory_dto.CommoditySpecificationInventoryPriceDTO;
import com.zhkj.vo.commoditySpeInvPrice_vo.CommoditySpeInvPriceVO;

import java.util.List;

public interface CommoditySpeInvPriceService {
    List<CommoditySpecificationInventoryPriceDTO> queryByInvPriceId(CommoditySpeInvPriceVO commoditySpeInvPriceVO);
}
