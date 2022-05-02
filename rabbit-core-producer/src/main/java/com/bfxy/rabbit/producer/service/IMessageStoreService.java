package com.bfxy.rabbit.producer.service;

import com.bfxy.rabbit.producer.entity.BrokerMessage;

import java.util.List;

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

    /**
     * 查询需要重试的数据
     * @return 返回数据
     * @param code 消息的状态
     */
    List<BrokerMessage> queryFailMessage(String code);

    /**
     * 更新为错误数据
     * @param messageId 消息的id
     * @param code 状态
     * @return 返回结果
     */
    int updateFileStatus(String messageId, String code);

    /**
     * 更新重试的次数
     * @param messageId 消息的id
     * @return 返回结果
     */
    int updateTryCount(String messageId);

}
