package com.zhkj.controller;

import com.zhkj.dto.inventory_dto.CommoditySpecificationInventoryPriceDTO;
import com.zhkj.service.CommoditySpeInvPriceServiceImpl;
import com.zhkj.vo.commoditySpeInvPrice_vo.CommoditySpeInvPriceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommoditySpeInvPriceController {
    @Autowired
    private CommoditySpeInvPriceServiceImpl commoditySpeInvPriceService;

    /**
     * 根据商品库存commoditySipId查询商品
     * @param id
     * @return
     */
    @GetMapping("/queryByInvPriceId")
    public  List<CommoditySpecificationInventoryPriceDTO> queryByInvPriceId(@RequestParam("id") Integer id){
        CommoditySpeInvPriceVO commoditySpeInvPriceVO=new CommoditySpeInvPriceVO();
        commoditySpeInvPriceVO.setId(id);
        List<CommoditySpecificationInventoryPriceDTO> list=commoditySpeInvPriceService.queryByInvPriceId(commoditySpeInvPriceVO);
        return list;
    }
}
