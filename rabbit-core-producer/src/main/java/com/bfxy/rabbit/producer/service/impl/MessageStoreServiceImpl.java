package com.bfxy.rabbit.producer.service.impl;

import com.bfxy.rabbit.producer.constant.BrokerMessageStatus;
import com.bfxy.rabbit.producer.entity.BrokerMessage;
import com.bfxy.rabbit.producer.mapper.BrokerMessageMapper;
import com.bfxy.rabbit.producer.service.IMessageStoreService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author Mengdexin
 * @date 2022 -04 -26 -22:09
 */
@Service
public class MessageStoreServiceImpl implements IMessageStoreService {

    @Autowired
    private BrokerMessageMapper brokerMessageMapper;

    @Override
    public BrokerMessage selectByPrimaryKey(String messageId) {
        return brokerMessageMapper.selectByPrimaryKey(messageId);
    }

    @Override
    public int insert(BrokerMessage brokerMessage) {
        return brokerMessageMapper.insert(brokerMessage);
    }

    @Override
    public int success(String messageId) {
        return brokerMessageMapper.updateMessageStatus(messageId,
                BrokerMessageStatus.SEND_OK.getCode(),
                new Date());
    }

    @Override
    public List<BrokerMessage> queryFailMessage(String code) {
        return brokerMessageMapper.selectFailMessage(code);
    }

    @Override
    public int updateFileStatus(String messageId, String code) {
        return brokerMessageMapper.updateMessageStatus(messageId, code, new Date());
    }

    @Override
    public int updateTryCount(String messageId) {
        return brokerMessageMapper.updateTryCount(messageId);
    }

}
