package com.zhkj.service.sharding_jdbc_key;
import com.zhkj.api.order_api.SimpleKey_Api;
import com.zhkj.mapper.order_mapper.OrderFromShopMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 主键生成业务
 */
@Service
public class SimpleKeyService implements SimpleKey_Api {
     @Autowired
    private OrderFromShopMapper orderFromShopMapper;
    /**
     * 查询最后一列id
     * @return
     */
    @Override
    public int orderfromshop0Entities(){
        return orderFromShopMapper.selectAllToOrderfromshop0().get(orderFromShopMapper.selectAllToOrderfromshop0().size()-1).getId();
    }
    /**
     * 查询最后一列id
     * @return
     */
    @Override
    public int orderfromshop1Entities(){
        return orderFromShopMapper.selectAllToOrderfromshop1().get(orderFromShopMapper.selectAllToOrderfromshop1().size()-1).getId();
    }

    /**
     * 生成主键
     * @return
     */
    @Override
    public int getKey() {
        int i = 1;
        int orderfromshop0Entities = 0;
        int orderfromshop1Entities = 0;
        try {
            orderfromshop1Entities = orderfromshop1Entities();
            orderfromshop0Entities = orderfromshop0Entities();
        } catch (Exception e) {

        }
        if (orderfromshop0Entities > orderfromshop1Entities) {
            i = orderfromshop0Entities + 1;
        } else if (orderfromshop0Entities < orderfromshop1Entities) {
            i = orderfromshop1Entities + 1;
        } else if (orderfromshop0Entities > 0 && orderfromshop1Entities == 0 || orderfromshop0Entities == 0 && orderfromshop1Entities > 0){
            i=i+1;
    }
    return i;
    }

}
