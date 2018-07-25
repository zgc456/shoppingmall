package com.zhkj.service;

import com.zhkj.dto.seek_dto.CommoditySpecificationInventoryPriceDTO;
import com.zhkj.dto.seek_dto.CommodityevaluationDTO;
import com.zhkj.service.entity.CommodityDetailsDTO;
import com.zhkj.service.entity.CommodityTemplate;
import com.zhkj.service.entity.SearchConditionPageVO;
import com.zhkj.util.ServiceMultiResult;

import java.io.IOException;
import java.util.List;

public interface ISearchService {
    /**
     * 查询所有类型商品
     * @return
     */
    List<ServiceMultiResult<CommodityTemplate>> getAllTypeCommodity();

    /**
     * 根据条件查询商品
     * @param condition 条件为null就查询所有commodity
     * @return
     */
    ServiceMultiResult<CommodityTemplate> byConditionSearchAllCommodity(SearchConditionPageVO condition);

    /**
     * 抢购查询
     * @param searchConditionPageVO
     * @return
     */
    ServiceMultiResult<CommodityTemplate> byDateSearchCommodity(SearchConditionPageVO searchConditionPageVO);

    /**
     * 根据商品ID展示商品详情
     * @param id
     * @return
     */
    CommodityDetailsDTO byIdSearchCommodity(Long id);

    /**
     * 抢购商品详情
     * @param id
     * @param startDate
     * @param endDate
     * @return
     */
    CommodityDetailsDTO byIdSearchRobCommodity(Long id,String startDate,String endDate);

    /**
     * 根据商品规格获得商品规格详细信息
     * @param commoditySpecificationInventoryPriceDTO 返回商品详细信息
     * @return
     */
    CommoditySpecificationInventoryPriceDTO byConditionGetCommoditySpecification(CommoditySpecificationInventoryPriceDTO commoditySpecificationInventoryPriceDTO);

    /**
     * 根据id获取商品评论
     * @param id
     * @return
     */
    List<CommodityevaluationDTO> byIdGetAllCommodityevaluation(String id);
}
