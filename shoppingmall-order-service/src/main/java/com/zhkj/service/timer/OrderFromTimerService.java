package com.zhkj.service.timer;

import com.zhkj.entity.OrderfromEntity;
import com.zhkj.mapper.order_mapper.OrderFromMapper;

import com.zhkj.service.HarvestAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *  定时修改数据库
 */
@Configuration
@Component
@EnableScheduling
public class OrderFromTimerService{
    static Logger logger = Logger.getLogger(OrderFromTimerService.class);
    @Autowired
    private OrderFromMapper orderFromMapper;
    public void execute() {
        Date nowDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:S");
        String nowTime = simpleDateFormat.format(nowDate);
        List<OrderfromEntity> orderFromList = orderFromMapper.selectByNowTime(nowTime);
        orderFromList.forEach(
                s ->{
                    if(nowDate.compareTo(s.getOrderEndTime()) == 1){
                        // 记录修改状态
                        orderFromMapper.updateByOrderFromNumber(s.getOrderNumber());
                        logger.info("订单"+s.getOrderNumber()+"已过期:修改订单成功");
                    }
                }
        );
    }
}
