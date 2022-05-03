package com.bfxy.rabbit.common.convert;

import com.google.common.base.Preconditions;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * 修饰者设计模式
 * @Author Mengdexin
 * @date 2022 -04 -26 -21:49
 */
public class RabbitMessageConverter implements MessageConverter {

    private GenericMessageConverter messageConverter;
    //	private final String delaultExprie = String.valueOf(24 * 60 * 60 * 1000);

    public RabbitMessageConverter(GenericMessageConverter messageConverter){
        Preconditions.checkNotNull(messageConverter);
        this.messageConverter = messageConverter;
    }

    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        //修饰者设计模式可以增加一些自己想要设置的参数
//        messageProperties.setExpiration(delaultExprie);
        com.bfxy.rabbit.api.Message message = (com.bfxy.rabbit.api.Message) o;
        messageProperties.setDelay(message.getDelayMills());
        return this.messageConverter.toMessage(o, messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        //反序列化为自己定义的对象
        com.bfxy.rabbit.api.Message msg = (com.bfxy.rabbit.api.Message) this.messageConverter.fromMessage(message);
        return msg;
    }
}
