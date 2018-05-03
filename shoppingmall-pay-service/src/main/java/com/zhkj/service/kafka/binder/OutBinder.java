package com.zhkj.service.kafka.binder;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * kafka用于发送消息
 */
public interface OutBinder {
    String SHOP_SERVER_OUTPUT="output";
    /**
     * 发消息的通道
     *
     * @return
     */
    @Output(SHOP_SERVER_OUTPUT)
    MessageChannel sendMessage();
}
