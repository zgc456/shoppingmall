package com.zhkj.controller;

import com.zhkj.dto.seek_dto.CommoditySpecificationInventoryPriceDTO;
import com.zhkj.dto.seek_dto.CommodityevaluationDTO;
import com.zhkj.service.ISearchService;
import com.zhkj.service.backstage.IBackstageHandleSearch;
import com.zhkj.service.entity.CommodityDetailsDTO;
import com.zhkj.service.entity.CommodityTemplate;
import com.zhkj.service.entity.SearchConditionPageVO;
import com.zhkj.util.ServiceMultiResult;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class ControllerTest {
    private static final Logger logger=LoggerFactory.getLogger(ControllerTest.class);
    @Autowired
    private IBackstageHandleSearch iBackstageHandleSearch;
    @Autowired
    ISearchService service;

    /**
     * 根据商品名字返回商品信息集合
     * @param searchConditionPageVO
     * @return
     */
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
     * 抢购列表
     * @param searchConditionPageVO 开始时间——结束时间
     * @return
     */
    @GetMapping("/byDateSearchCommodity")
    public ServiceMultiResult<CommodityTemplate> byDateSearchCommodity(@ModelAttribute SearchConditionPageVO searchConditionPageVO){
        return service.byDateSearchCommodity(searchConditionPageVO);
    }

    /**
     * 商品详情 根据商品Id
     * @param searchConditionPageVO
     * @return
     */
    @GetMapping("/byIdGetCommodityDetails")
    public CommodityDetailsDTO byIdGetCommodityDetails(@ModelAttribute SearchConditionPageVO searchConditionPageVO){
        return service.byIdSearchCommodity(Long.parseLong(searchConditionPageVO.getId()));
    }

    /**
     * 根据规格和商品ID,返回商品规格细节信息
     * @param commoditySpecificationInventoryPriceDTO
     * @return
     */
    @GetMapping("/getCommoditySpecification")
    public CommoditySpecificationInventoryPriceDTO getCommoditySpecification(@ModelAttribute CommoditySpecificationInventoryPriceDTO commoditySpecificationInventoryPriceDTO){
            return service.byConditionGetCommoditySpecification(commoditySpecificationInventoryPriceDTO);
    }

    /**
     * 抢购商品详情
     * @param searchConditionPageVO 商品id，开始时间，结束时间，
     * @return
     */
    @GetMapping("/getRobCommodityDatails")
    public CommodityDetailsDTO getRobCommodityDatails(SearchConditionPageVO searchConditionPageVO ){
        String id=searchConditionPageVO.getId();
        String star=searchConditionPageVO.getStartDate();
        String end=searchConditionPageVO.getEndDate();
        if (Strings.isBlank(id)||Strings.isBlank(star)||Strings.isBlank(end)){
            logger.warn("parameter error");
        }
        return service.byIdSearchRobCommodity(Long.parseLong(id),star,end);
    }
    /**
     * 根据商品id返回商品评论
     * @param id
     * @return
     */
    @GetMapping("/getCommodityevaluation")
    public List<CommodityevaluationDTO> getCommodityevaluation(String id){
        return service.byIdGetAllCommodityevaluation(id);
    }

//    public boolean deleteCommodity(){
//        service
//    }
}