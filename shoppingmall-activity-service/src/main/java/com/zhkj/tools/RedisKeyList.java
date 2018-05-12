package com.zhkj.tools;

import com.zhkj.entity.PromotionitemEntity;
import com.zhkj.mapper.activity_mapper.PromotionitemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class RedisKeyList {
    public static List<String> redisKeyList=null;
    @Autowired
   private PromotionitemMapper promotionitemMapper;
    public void RedisKeyLists(){
        redisKeyList=new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(new Date());
       List<PromotionitemEntity> list= promotionitemMapper.selectByTodayShop(today);
        for (PromotionitemEntity list1:list
             ) {
            redisKeyList.add(String.valueOf(list1.getStartTime().getHours()));
        }
    }
}
