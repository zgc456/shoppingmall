package com.zhkj.compound;

import com.zhkj.service.DealQueueThread;
import com.zhkj.vo.activity_vo.UserVo;
import org.redisson.api.RDeque;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 *这个类用于开放抢购给前台调用
 */
@RestController
public class EushTopurChase {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedissonClient redisson;
    //设置队列为空
    private static RDeque<UserVo> buyqueue =null;//线程安全的请求队列
    int i=1;
    //熔断器 如果主线程或者子线程 失败直接返回前台 活动,太火爆
    //name 客户信息唯一信息
    //qinanggou 抢购的商品在redis中的key
    @RequestMapping("/addOrders.do")
    @ResponseBody
    public Object addOrders(UserVo userVo){
        //用于给线程中的stringRedisTemplate赋值
        DealQueueThread.stringRedisTemplate=this.stringRedisTemplate;
        i++;
        userVo=new UserVo();
        userVo.setQianggou("oppor9");
        userVo.setName("zhangsan"+i);
        Map<String, Object> results = new HashMap<>();
        try {
            //下订单之前，先获取商品的剩余数量
            int residue = Integer.valueOf(stringRedisTemplate.opsForValue().get("count1"));
            if(residue<1){//如果剩余数量不足，直接响应客户端“卖完了”
                //返回卖完了 给前端
                results.put("msg", "卖完了");
                results.put("done", false);
                return results;
            }
            //如果还有剩余商品,就准备将请求放到请求队列中
            if(buyqueue==null){//第一次初始化请求队列,队列的容量为当前的商品剩余数量
                buyqueue=redisson.getDeque(userVo.getQianggou());
            }
            try {
                buyqueue.addLast(userVo);//当队列的可用容量大于0时,将请求放到请求队列中
                //输出队列的信息
//                    for ( UserVo voRDeque:buyqueue
//                            ) {
//                        System.out.println(voRDeque.getName());
//                    }
            }catch (Exception e){
                //当请求队列已满,本次请求不能处理,直接响应客户端提示请求队列已满
                results.put("msg", "活动太火爆了");
                results.put("done", false);
                return results;
            }

            if(!DealQueueThread.excute){//如果线程类的当前执行标志为未执行,即空闲状态,启动方法
                DealQueueThread dealQueue = new DealQueueThread(buyqueue);
//               new Thread(dealQueue).start();
                dealQueue.run();
//                ThreadPoolUtil.pool.execute(dealQueue);
//                BaseLog.info("Thread.activeCount()="+Thread.activeCount());
                //请求放入到队列中，即完成下单请求
                results.put("done", true);
                results.put("msg", "下订单成功");
            }else{
                System.out.println("太火爆了");
                return null ;
            }
        } catch (Exception e) {
            results.put("done", false);
            results.put("msg", "下单失败");
//            BaseLog.info("addOrders results="+JSON.toJSONString(results));
//            BaseLog.error("addOrders",e);
        }finally{
            //    jedisPool.returnResource(jedis);
        }
        return results;
    }
}
