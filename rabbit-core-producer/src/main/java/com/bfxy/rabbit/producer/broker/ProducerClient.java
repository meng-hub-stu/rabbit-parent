package com.bfxy.rabbit.producer.broker;

import com.bfxy.rabbit.api.Message;
import com.bfxy.rabbit.api.MessageProducer;
import com.bfxy.rabbit.api.MessageType;
import com.bfxy.rabbit.api.SendCallback;
import com.bfxy.rabbit.api.exception.MessageRunTimeException;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 发送消息的实现类
 * @Author Mengdexin
 * @date 2022 -04 -18 -22:18
 */
@Component
public class ProducerClient implements MessageProducer {

    @Autowired
    private RabbitBroker rabbitBroker;

    @Override
    public void send(Message message) throws MessageRunTimeException {
        Preconditions.checkNotNull(message);
        String messageType = message.getMessageType();
        //发送三种类型的消息
        switch (messageType) {
            case MessageType.RAPID:
                rabbitBroker.rapidSend(message);
                break;
            case MessageType.CONFIRM:
                rabbitBroker.confirmSend(message);
                break;
            case MessageType.RELIANT:
                rabbitBroker.reliantSend(message);
                break;
            default:
                break;
        }

    }

    @Override
    public void send(List<Message> messages) throws MessageRunTimeException {
        messages.forEach(message -> {
            message.setMessageType(MessageType.CONFIRM);
            MessageHolder.add(message);
        });
        rabbitBroker.sendMessages();
    }

    @Override
    public void send(Message message, SendCallback sendCallback) throws MessageRunTimeException {
    }

}
