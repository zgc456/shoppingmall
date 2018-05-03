package com.zhkj.compound;

import com.alibaba.fastjson.JSON;
import com.zhkj.service.kafka.OutputService;
import com.zhkj.service.order_from_service.OrderFromUpdate;
import com.zhkj.vo.order_from_shop_vo.OrderFromVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestService {
    @Autowired
    OrderFromUpdate orderFromUpdate;
    @Autowired
    OutputService outputService;
    @GetMapping("/updateShopApi/json/{json}")
    public int updateShopApi(@PathVariable String json){
        OrderFromVo orderFromVo = JSON.parseObject(json,OrderFromVo.class);
        return orderFromUpdate.updateShopApi(orderFromVo);
    }
    @RequestMapping("/kafka")
    public int kafka(){
        return outputService.send();
    }
}
