package com.bfxy.rabbit.common.convert;

import com.bfxy.rabbit.common.serializer.Serializer;
import com.google.common.base.Preconditions;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * 进行序列化，将Spring中原带的序列化方式转成自己定义的序列化方式
 * @Author Mengdexin
 * @date 2022 -04 -26 -21:37
 */
public class GenericMessageConverter implements MessageConverter {

    private Serializer serializer;

    public GenericMessageConverter(Serializer serializer){
        Preconditions.checkNotNull(serializer);
        this.serializer = serializer;
    }

    @Override
    public Message toMessage(Object o, MessageProperties messageProperties) throws MessageConversionException {
        return new Message(this.serializer.serializeRaw(o), messageProperties);
    }

    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        return this.serializer.deserialize(message.getBody());
    }

}
