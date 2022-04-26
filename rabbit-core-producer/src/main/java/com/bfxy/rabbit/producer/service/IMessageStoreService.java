package com.bfxy.rabbit.producer.service;

import com.bfxy.rabbit.producer.entity.BrokerMessage;

/**
 * @Author Mengdexin
 * @date 2022 -04 -26 -22:08
 */
public interface IMessageStoreService {

    /**
     * 查询消息
     * @param messageId
     * @return
     */
    BrokerMessage selectByPrimaryKey(String messageId);

    /**
     * 插入消息
     * @param brokerMessage 插入内容
     * @return 返回结果
     */
    int insert(BrokerMessage brokerMessage);

    /**
     * 修改状态
     * @param messageId 消息id
     */
    int success(String messageId);

}
