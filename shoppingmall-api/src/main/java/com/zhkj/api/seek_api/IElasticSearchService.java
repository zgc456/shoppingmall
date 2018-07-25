package com.zhkj.api.seek_api;

import com.zhkj.dto.seek_dto.CommoditySpecificationInventoryPriceDTO;

import java.io.IOException;

/**
 * ${user}
 *
 * @Author: Jiankang.Ren
 * @Description:
 * @Date: Created in 15:13 2018/7/25 0025
 */
public interface IElasticSearchService {
    /**
     * 根据商品规格id获取购买商品规格
     * @param id
     * @param isRob
     * @return
     * @throws IOException
     */
    CommoditySpecificationInventoryPriceDTO byCommoditySpecificationInventoryPrice(long id, boolean isRob) throws IOException;
}
