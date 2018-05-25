package com.zhkj.controller;

import com.zhkj.dto.seek_dto.CommoditySpecificationInventoryPriceDTO;
import com.zhkj.dto.seek_dto.CommodityevaluationDTO;
import com.zhkj.service.ISearchService;
import com.zhkj.service.backstage.IBackstageHandleSearch;
import com.zhkj.service.entity.CommodityDetailsDTO;
import com.zhkj.service.entity.CommodityTemplate;
import com.zhkj.service.entity.SearchConditionPageVO;
import com.zhkj.util.ServiceMultiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ControllerTest {
    @Autowired
    private IBackstageHandleSearch iBackstageHandleSearch;
    @Autowired
    ISearchService service;
    @GetMapping("/searchByCondition")
    public ServiceMultiResult<CommodityTemplate> searchByCondition(@ModelAttribute SearchConditionPageVO searchConditionPageVO){
        return service.byConditionSearchAllCommodity(searchConditionPageVO);
    }

    /**
     * 查询所有商品并且归纳好分类
     * @return
     */
    @GetMapping("/searchAllCommodity")
    public List<ServiceMultiResult<CommodityTemplate>> searchAllCommodity(){
        return service.getAllTypeCommodity();
    }

    /**
     * 抢购
     * @param searchConditionPageVO
     * @return
     */
    @GetMapping("/byDateSearchCommodity")
    public ServiceMultiResult<CommodityTemplate> byDateSearchCommodity(@ModelAttribute SearchConditionPageVO searchConditionPageVO){
        return service.byDateSearchCommodity(searchConditionPageVO);
    }

    @GetMapping("/byIdGetCommodityDetails")
    public CommodityDetailsDTO byIdGetCommodityDetails(@ModelAttribute SearchConditionPageVO searchConditionPageVO){
        return service.byIdSearchCommodity(Long.parseLong(searchConditionPageVO.getId()));
    }
    @GetMapping("/getCommoditySpecification")
    public CommoditySpecificationInventoryPriceDTO getCommoditySpecification(@ModelAttribute CommoditySpecificationInventoryPriceDTO commoditySpecificationInventoryPriceDTO){
        if (commoditySpecificationInventoryPriceDTO!=null){
            return null;
        }else{
            return null;
        }
    }
    @GetMapping("/getCommodityevaluation")
    public List<CommodityevaluationDTO> getCommodityevaluation(@ModelAttribute String id){
        return service.byIdGetAllCommodityevaluation(id);
    }
}