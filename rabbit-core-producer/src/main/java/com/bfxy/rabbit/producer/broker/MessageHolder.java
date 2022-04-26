package com.bfxy.rabbit.producer.broker;

import com.bfxy.rabbit.api.Message;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Author Mengdexin
 * @date 2022 -04 -26 -22:53
 */
public class MessageHolder {

    private List<Message> messages = Lists.newArrayList();

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static final ThreadLocal<MessageHolder> holder = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return new MessageHolder();
        }
    };

    public static void add(Message message) {
        holder.get().messages.add(message);
    }

    public static List<Message> clear(){
        List<Message> messages = Lists.newArrayList(holder.get().messages);
        holder.remove();
        return messages;
    }

}
