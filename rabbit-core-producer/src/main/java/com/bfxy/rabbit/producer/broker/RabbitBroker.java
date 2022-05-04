package com.bfxy.rabbit.producer.broker;

import com.bfxy.rabbit.api.Message;

/**
 * 不同类型发送不同的消息
 * @Author Mengdexin
 * @date 2022 -04 -18 -22:36
 */
public interface RabbitBroker {
    /**
     * 迅速的消息
     * @param message 消息内容
     */
    void rapidSend(Message message);

    /**
     * 发送确认消息
     * @param message 消息内容
     */
    void confirmSend(Message message);

    /**
     * 发送可靠性消息
     * @param message 消息内容
     */
    void reliantSend(Message message);

    /**
     * 批量发送消息
     */
    void sendMessages();

}
