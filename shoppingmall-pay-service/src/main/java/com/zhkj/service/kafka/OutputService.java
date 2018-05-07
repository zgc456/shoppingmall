package com.zhkj.service.kafka;

import com.zhkj.service.kafka.binder.OutBinder;
import org.apache.log4j.Logger;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * kafka发送消息 使用
 */
@Service
public class OutputService {
//    final static Logger logger = Logger.getLogger(OutputService.class);
//    @Resource(name = OutBinder.SHOP_SERVER_OUTPUT)
//    private MessageChannel shopMessage;
//    /**
//     * 发送消息
//     * @return
//     */
//    public int send(){
//        try {
//            shopMessage.send(MessageBuilder.withPayload("撒大苏打").build(), 3000);
//            return 1;  //表示发送成功
//        }catch (Exception e){
//            logger.debug(e+"订单成功kafka发送消息失败");
//             return 0; //表示发送失败
//        }
//    }
}
