package com.zhkj.service;

import com.alibaba.fastjson.JSON;
import com.zhkj.api.activity_api.RedisCache_Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 这个类用于做redis操作
 */
@Service
public class RedisCacheService implements RedisCache_Api {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private Map map = null;
    //查找redis
    @Override
    public Map getRedis(String key) {
        HashMap<String, Object> redisCache = new HashMap<>();
        String json = stringRedisTemplate.opsForValue().get(key);
        map = (Map) JSON.parseObject(json);
        return map;
    }
    //修改redis
    @Override
    public void updateRedis(String key, String id, int count) {
        if (map == null) {
            //抛出异常 日志打印非法操作 updateRedis
        }
        //遍历map
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            //创建entry 来操作 key value
            Map.Entry entry = (Map.Entry) iter.next();
            String entryId = (String) entry.getKey();
            //如果key和操作的传入的key相同
            if (entryId.equals(id)) {
                //拿到遍历的value map 修改数据
                ((Map) entry.getValue()).put("commodityNumber", count);
                String json = JSON.toJSON(map).toString();
                stringRedisTemplate.opsForValue().set(key, json);
                break;
            }
        }
    }
    //查找库存数量
    @Override
    public int countRedisCache(String id) {
        if (map == null) {
            //抛出异常 日志打印非法操作 updateRedis
        }
        //遍历map
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            //创建entry 来操作 key value
            Map.Entry entry = (Map.Entry) iter.next();
            String entryId = (String) entry.getKey();
            //如果key和操作的传入的key相同
            if (entryId.equals(id)) {
                //拿到遍历的value map 查找数量
                return (int) ((Map) entry.getValue()).get("commodityNumber");
            }
        }
        return -1;
    }
}
