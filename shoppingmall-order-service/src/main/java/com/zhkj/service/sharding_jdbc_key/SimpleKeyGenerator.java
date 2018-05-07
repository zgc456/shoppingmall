package com.zhkj.service.sharding_jdbc_key;

import com.zhkj.entity.Orderfromshop0Entity;
import com.zhkj.entity.Orderfromshop1Entity;
import com.zhkj.mapper.order_mapper.OrderFromShopMapper;
import io.shardingjdbc.core.keygen.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设置key分表规则
 */
@Service
public class SimpleKeyGenerator implements KeyGenerator {
    @Autowired
    private OrderFromShopMapper orderFromShopMapper;
    int i = 1;

    @Override
    public Number generateKey() {

        return i++;
    }
}
