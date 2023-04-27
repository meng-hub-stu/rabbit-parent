package com.bfxy.rabbit.producer.broker;

import com.bfxy.rabbit.api.Message;
import com.bfxy.rabbit.api.MessageType;
import com.bfxy.rabbit.api.exception.MessageRunTimeException;
import com.bfxy.rabbit.common.convert.GenericMessageConverter;
import com.bfxy.rabbit.common.convert.RabbitMessageConverter;
import com.bfxy.rabbit.common.serializer.Serializer;
import com.bfxy.rabbit.common.serializer.SerializerFactory;
import com.bfxy.rabbit.common.serializer.impl.JacksonSerializerFactory;
import com.bfxy.rabbit.producer.service.IMessageStoreService;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * rabbitTemplate的容器封装
 * @Author Mengdexin
 * @date 2022 -04 -18 -22:24
 */
@Slf4j
@Component
public class RabbitTemplateContainer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private IMessageStoreService messageStoreService;

    private Map<String, RabbitTemplate> rabbitMap = Maps.newConcurrentMap();

    private Splitter splitter = Splitter.on("#");

    private SerializerFactory serializerFeature = JacksonSerializerFactory.INSTANCE;


    public RabbitTemplate getTemplate(Message message) throws MessageRunTimeException {
        Preconditions.checkNotNull(message);
        String topic = message.getTopic();
        RabbitTemplate rabbitTemplate = rabbitMap.get(topic);
        if (rabbitTemplate != null) {
            return rabbitTemplate;
        }
        log.info("@RabbitTemplateContainer.getTemplate# topic:{} is not exits, create one", topic);
        //创建rabbitTemplate
        RabbitTemplate newTemplate = new RabbitTemplate(connectionFactory);
        newTemplate.setExchange(topic);
        newTemplate.setRoutingKey(message.getRoutingKey());
        newTemplate.setRetryTemplate(new RetryTemplate());
        //TODO 进行序列化message
        Serializer serializer = serializerFeature.create();
        GenericMessageConverter gmc = new GenericMessageConverter(serializer);
        RabbitMessageConverter rmc = new RabbitMessageConverter(gmc);
        newTemplate.setMessageConverter(rmc);
        //迅速消息不需要进行确认
        String messageType = message.getMessageType();
        if (!MessageType.RAPID.equals(messageType)) {
            newTemplate.setConfirmCallback(this);
        }
        newTemplate.setMandatory(true);
        newTemplate.setReturnCallback(this);
        rabbitMap.putIfAbsent(topic, newTemplate);
        return rabbitMap.get(topic);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        //具体的消息应答
        List<String> strings = splitter.splitToList(correlationData.getId());
        String messageId = strings.get(0);
        long sendTime = Long.parseLong(strings.get(1));
        String messageType = strings.get(2);
        if (ack) {
            // 	如果当前消息类型为reliant 我们就去数据库查找并进行更新
            if(MessageType.RELIANT.endsWith(messageType)) {
                this.messageStoreService.success(messageId);
            }
            log.info("send message is OK, confirm messageId: {}, sendTime: {}", messageId, sendTime);
        } else {
            log.error("send message is Fail, confirm messageId: {}, sendTime: {}", messageId, sendTime);
        }
    }

    @Override
    public void returnedMessage(org.springframework.amqp.core.Message message, int i, String s, String s1, String s2) {
        log.error("发生错误");
    }

}
