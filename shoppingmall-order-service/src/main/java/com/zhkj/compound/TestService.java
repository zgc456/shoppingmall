package com.zhkj.compound;

import com.zhkj.entity.Orderfromshop0Entity;
import com.zhkj.mapper.pay_mapper.OrderFromShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lenovo on 2018/4/16.
 */
@RestController
public class TestService {
    @Autowired
    OrderFromShopMapper testPayMapper;
    @RequestMapping("/a")
        public String a(){
        for (int i=1;i<10;i++){
        Orderfromshop0Entity orderfromshop0Entity=new Orderfromshop0Entity();
        orderfromshop0Entity.setLogisticsTypeId(1);
        orderfromshop0Entity.setOrderFromId(1);
        orderfromshop0Entity.setFeight(1);
        orderfromshop0Entity.setCommodityPrice(1);
        orderfromshop0Entity.setCommodityNumber(1);
        orderfromshop0Entity.setCommodityId(1);
        testPayMapper.insert(orderfromshop0Entity);}
        return "1";
    }
}
