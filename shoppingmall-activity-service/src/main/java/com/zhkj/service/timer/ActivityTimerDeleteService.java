package com.zhkj.service.timer;

import com.alibaba.fastjson.JSON;
import com.zhkj.entity.PromotionitemEntity;
import com.zhkj.mapper.activity_mapper.PromotionitemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.*;

/**
 * 更新数据库数据
 *  定时任务,每天凌晨过后执行
 */
@Configuration
public class ActivityTimerDeleteService {

    private static Logger logger = LoggerFactory.getLogger(ActivityTimerDeleteService.class);

    @Autowired
    private PromotionitemMapper promotionitemMapper;
    /**
     * 读取Redis 数据
     */
    public void getRedisSource(StringRedisTemplate stringRedisTemplate){
        List<PromotionitemEntity> redisHashMap = new ArrayList<>();
        Set<String> strings = stringRedisTemplate.keys("*");
        if(null != strings && strings.size() > 0){
            List<String> keys = new ArrayList<>();
            strings.forEach(set->{ keys.add(set); });
            HashMap<String,PromotionitemEntity> promotionItemHashMap = new HashMap<>();
            for(int i = 0;i < keys.size();i++){
                String timeKey = keys.get(i);
                String timeValue = stringRedisTemplate.opsForValue().get(timeKey);
                promotionItemHashMap = JSON.parseObject(timeValue,HashMap.class);
                if(null != promotionItemHashMap && promotionItemHashMap.size() > 0){
                    for(HashMap.Entry<String,PromotionitemEntity> hash : promotionItemHashMap.entrySet()){
                        Object value = hash.getValue();
                        PromotionitemEntity promotionitemEntity = JSON.parseObject(value.toString(),PromotionitemEntity.class);
                        redisHashMap.add(promotionitemEntity);
                    }
                }
            }
            if(null != redisHashMap && redisHashMap.size() > 0){
                logger.info("读取 Redis 数据成功,作用于凌晨修改数据库信息");
                updateMySqlPromotionItem(redisHashMap);
            }else {
                logger.info("读取 Redis 数据失败,作用于凌晨修改数据库信息");
            }
        }else {
            logger.info("读取 Redis 数据失败");
        }
    }

    /**
     * 修改数据表(promotionitem)数据
     * @param hashMap reids读取到的当天数据
     */
    private void updateMySqlPromotionItem(List<PromotionitemEntity> hashMap){
        if(null != hashMap && hashMap.size() > 0){
            int index = 0;
            int size = hashMap.size();
            for(int i = 0;i < hashMap.size();i++){
                int result = promotionitemMapper.updateById(hashMap.get(i));
                if(result > 0){
                    index++;
                }
            }
            logger.info("凌晨数据库修改 商品总数:{},成功:{}",size,index);
        }
    }
}
