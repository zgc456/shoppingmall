package com.zhkj.compound;

import com.alibaba.fastjson.JSON;
import com.zhkj.result.ResultAll;
import com.zhkj.result.ResultUtils;
import com.zhkj.service.DealQueueThread;
import com.zhkj.service.RedisCacheService;
import com.zhkj.tools.RedisKeyList;
import com.zhkj.vo.activity_vo.UserVo;
import org.redisson.api.RDeque;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 这个类用于开放抢购给前台调用
 */
@RestController
public class EushTopurChase {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedissonClient redisson;
    @Autowired
    private RedisCacheService redisCacheService;
    @Autowired
    private RedisKeyList redisKeyList;
    ResultUtils resultUtils=new ResultUtils();
    //设置队列为空
    private static RDeque<UserVo> buyqueue = null;//线程安全的请求队列
    Logger logger=LoggerFactory.getLogger(EushTopurChase.class);
    /**
     * {"name": "张三","hoppingName": "1","startTime": "13"}
     * @param
     * @return
     */
    //@RequestMapping("/addOrders.do/json/{json}")
    @RequestMapping("/addOrders.do")
    public ResultAll addOrders(@RequestBody UserVo userVo) {

       // UserVo userVo = JSON.parseObject(json, UserVo.class);
        //将所有redis中的key存入redis
        redisKeyList.RedisKeyLists();
        Date date=new Date();
        int hours=date.getHours();
        if (userVo.getStartTime()!=hours){
            logger.error("非法参数拼接");
            resultUtils.resultAll(-1,"非法参数拼接",null);
        }
        //先让程序知道这个时间段都有什么商品
        redisCacheService.getRedis(String.valueOf(userVo.getStartTime()));
        //用于给线程中的stringRedisTemplate赋值
        DealQueueThread.stringRedisTemplate = this.stringRedisTemplate;
        DealQueueThread.redisCacheService=this.redisCacheService;

        Map<String, Object> results = new HashMap<>();
        try {
            //下订单之前，先获取商品的剩余数量
            int residue = redisCacheService.countRedisCache(String.valueOf(userVo.getHoppingName()));
            if (residue < 1) {//如果剩余数量不足，直接响应客户端“卖完了”
                //返回卖完了 给前端
                results.put("msg", "卖完了");
                results.put("done", false);
                return resultUtils.resultAll(-1,"卖完了",results);
            }
            //如果还有剩余商品,就准备将请求放到请求队列中
            if (buyqueue == null) {//第一次初始化请求队列,队列的容量为当前的商品剩余数量
                buyqueue = redisson.getDeque(String.valueOf(userVo.getHoppingName()));
            }
            try {
                buyqueue.addLast(userVo);//当队列的可用容量大于0时,将请求放到请求队列中

            } catch (Exception e) {
                //当请求队列已满,本次请求不能处理,直接响应客户端提示请求队列已满
                results.put("msg", "活动太火爆了");
                results.put("done", false);
                return resultUtils.resultAll(-1,"太火爆了",results);
            }

            if (!DealQueueThread.excute) {//如果线程类的当前执行标志为未执行,即空闲状态,启动方法
                DealQueueThread dealQueue = new DealQueueThread(buyqueue);
//               new Thread(dealQueue).start();
                dealQueue.run();
//                ThreadPoolUtil.pool.execute(dealQueue);
//                BaseLog.info("Thread.activeCount()="+Thread.activeCount());
                //请求放入到队列中，即完成下单请求
                results.put("done", true);
                results.put("msg", "下订单成功");
            } else {
                System.out.println("太火爆了");
                return null;
            }
        } catch (Exception e) {
            results.put("done", false);
            results.put("msg", "下单失败");
//            BaseLog.info("addOrders results="+JSON.toJSONString(results));
//            BaseLog.error("addOrders",e);
        } finally {
            //    jedisPool.returnResource(jedis);
        }
           return resultUtils.resultAll(1,"",results);
    }
    /**
     * 给前台返回
     */
    @RequestMapping("/selectMessage")
    public String getRedisKeyToString(){
        String a=null;
        redisKeyList.RedisKeyLists();
        for (String list:RedisKeyList.redisKeyList
             ) {
             a=stringRedisTemplate.opsForValue().get(list)+",";
        }
         return a;

    }

}
