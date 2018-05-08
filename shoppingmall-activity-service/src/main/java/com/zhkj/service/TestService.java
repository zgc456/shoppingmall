package com.zhkj.service;

import com.alibaba.fastjson.JSON;
import com.zhkj.vo.activity_vo.RedisCacheObject;
import com.zhkj.vo.activity_vo.RedisCacheVo;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.*;

@RestController
public class TestService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
   @Autowired
   private RedisCacheService redisCacheService;
    /**
     * 向redis存储数据
     * @return
     */
    @RequestMapping("/a")
    public String name(){

        //创建数据库集实体对象
        RedisCacheObject redisCacheObject=new RedisCacheObject();
        //创建数据库集实体对象
        RedisCacheObject redisCacheObject1=new RedisCacheObject();

       redisCacheObject.setCommodityId(1);
       redisCacheObject.setCommodityNumber(100);
       redisCacheObject.setCommodityId(1);
       redisCacheObject.setDiscountPrice(12.00);
       redisCacheObject.setEndTime(new Timestamp(2018,5,8,7,7,7,7));
       redisCacheObject.setStartTime(new Timestamp(2018,5,8,7,7,7,7));
        redisCacheObject1.setCommodityId(2);
        redisCacheObject1.setCommodityNumber(100);
        redisCacheObject1.setCommodityId(2);
        redisCacheObject1.setDiscountPrice(13.00);
        redisCacheObject1.setEndTime(new Timestamp(2018,5,8,7,7,7,7));
        redisCacheObject1.setStartTime(new Timestamp(2018,5,8,7,7,7,7));

      //创建集合以id外键为key 实体类为value 存入相同的时间
       HashMap<String ,RedisCacheObject> h=new HashMap<String ,RedisCacheObject>();
       h.put("1",redisCacheObject);
       h.put("2",redisCacheObject1);
        //创建集合以id外键为key 实体类为value 存入相同的时间
        HashMap<String ,RedisCacheObject> h1=new HashMap<String ,RedisCacheObject>();
        h1.put("1",redisCacheObject);
        h1.put("2",redisCacheObject1);
       //将两个不同时间的map存入list
        List<HashMap> list=new ArrayList<>();
        list.add(h);
        list.add(h1);
        //设置开始时间 将list存入map
        HashMap<String ,Object> redisCacheMap=new HashMap<String ,Object>();
        redisCacheMap.put("13",h);
        redisCacheMap.put("14",h1);
            Iterator iter = redisCacheMap.entrySet().iterator();
            while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
                String json=  JSON.toJSON(entry.getValue()).toString();
                stringRedisTemplate.opsForValue().set((String) entry.getKey(),json);
            }



        return "true";
    }
    @RequestMapping("/b")
    public Map b(){
       return redisCacheService.getRedis(13+"");
    }
}
