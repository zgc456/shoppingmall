package com.zhkj.api.activity_api;

import com.zhkj.dto.activity_dto.RedisCacheObject;

import java.util.Map;

/**
 * 做redis接口
 */
public interface RedisCache_Api {
    /**
     * 获取当前时间段的redis
     */
    public Map getRedis(String key);

    /**
     * 修改redis
     */
    public void updateRedis(String key,String id,int count);

    /**
     * 查找redis库存剩余数量
     * @param
     * @param id 外键key 代表哪个商品
     * @return
     */
    public int countRedisCache( String id);
}
