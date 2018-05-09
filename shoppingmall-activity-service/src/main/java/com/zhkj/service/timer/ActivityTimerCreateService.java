package com.zhkj.service.timer;

import com.alibaba.fastjson.JSON;
import com.zhkj.entity.PromotionitemEntity;
import com.zhkj.mapper.activity_mapper.PromotionitemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * redis 促销商品存储
 *  定时任务,每天凌晨过后执行
 */
@Component
@Configuration
@EnableScheduling
public class ActivityTimerCreateService {
    private static Logger logger = LoggerFactory.getLogger(ActivityTimerCreateService.class);
    @Autowired
    private PromotionitemMapper promotionitemMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ActivityTimerDeleteService activityTimerDeleteService;
    public void execute(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today = simpleDateFormat.format(new Date());
        List<PromotionitemEntity> promotionitemEntityList = promotionitemMapper.selectByTodayShop(today);
        activityTimerDeleteService.getRedisSource(stringRedisTemplate);
       participle(promotionitemEntityList);
    }

    /**
     * 为促销商品按时间分类
     * @param promotionitemEntities 促销商品列表
     * @return 分过后的促销商品
     */
    private void participle(List<PromotionitemEntity> promotionitemEntities){
        if(null != promotionitemEntities && promotionitemEntities.size() > 0){
            int mapKey = 0;
            int startIHours = 0;
            int start1IHours = 0;
            HashMap hashMap = new HashMap();
            HashMap<String,PromotionitemEntity> forEachList = new HashMap<>();
            Timestamp timestamp = new Timestamp(new Date().getTime());
            for(int i = 0;i < promotionitemEntities.size();i++){
                if(!(promotionitemEntities.get(i).getStartTime().getDate() > timestamp.getDate())){
                    forEachList.put(promotionitemEntities.get(i).getId() + "",promotionitemEntities.get(i));
                    startIHours = promotionitemEntities.get(i).getStartTime().getHours();
                    if(mapKey == startIHours){
                        if(i != (promotionitemEntities.size() - 1)){
                            start1IHours = promotionitemEntities.get(i + 1).getStartTime().getHours();
                        }
                        if(mapKey != start1IHours){
                            hashMap.put(mapKey + "",forEachList);
                            mapKey = start1IHours;
                            forEachList = new HashMap<>();
                        }else if(i == (promotionitemEntities.size() -1)){
                            hashMap.put(mapKey + "",forEachList);
                        }
                    }else if(mapKey == 0){
                        mapKey = startIHours;
                    }
                }
            }
            if(null != hashMap && hashMap.size() > 0){
                logger.info("促销商品存储成功,共{}件商品",promotionitemEntities.size());
                storageRedis(hashMap);
            }else {
                logger.info("促销商品存储失败,作用于凌晨存储促销商品");
            }
        }else {
            logger.info("促销商品未查出,作用于凌晨存储促销商品");
        }
    }

    /**
     * 向redis存储数据
     * @param hashMap 促销商品数据
     * @return 是否成功
     */
    private void storageRedis(HashMap<String,Object> hashMap){
        try{
            if(null != hashMap && hashMap.size() > 0){
                for(HashMap.Entry<String,Object> entry : hashMap.entrySet()){
                    String valueJson = JSON.toJSON(entry.getValue()).toString();
                    stringRedisTemplate.opsForValue().set(entry.getKey(),valueJson);
                }
                logger.info("促销数据存储成功,作用于凌晨存储促销商品");
            }
        }catch (Exception e){
            logger.info("促销数据存储失败,作用于凌晨存储促销商品");
        }
    }
}
