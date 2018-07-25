package com.zhkj.controller;



import com.zhkj.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuhao.wang
 */
@RestController
public class StockController {

    @Autowired
    private StockService stockService;
    @Autowired
    private CommoditySpeInvPriceController commoditySpeInvPriceController;

    @RequestMapping(value = "stock", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object stock(@RequestParam("commoditySipId") Integer commoditySipId,@RequestParam("commodityNumber") Integer commodityNumber) {
        // 商品ID

        // 库存ID
        String redisKey = "redis_key:stock:" + commoditySipId;
        long stock = stockService.stock(redisKey, 60 * 60, commodityNumber, () -> initStock(commoditySipId));
        return stock >= 0;
    }

    /**
     * 获取初始的库存
     *
     * @return
     */
    public int initStock(Integer commoditySipId) {
        // TODO 这里做一些初始化库存的操作
//        return commoditySpeInvPriceController.queryByInvPriceId(commoditySipId);
        return commoditySpeInvPriceController.getInv(commoditySipId);
    }

    @RequestMapping(value = "getStock", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object getStock(@RequestParam("commoditySipId") Integer commoditySipId) {
        // 商品ID
//        long commoditySipId = 1;
        // 库存ID
        String redisKey = "redis_key:stock:" + commoditySipId;

        return stockService.getStock(redisKey);
    }

    @RequestMapping(value = "addStock", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Object addStock(@RequestParam("commoditySipId") Integer commoditySipId,@RequestParam("commodityNumber") Integer commodityNumber) {
        // 商品ID
//        long commoditySipId = 1;
        // 库存ID
        String redisKey = "redis_key:stock:" + commoditySipId;

        return stockService.addStock(redisKey, commodityNumber);
    }
}