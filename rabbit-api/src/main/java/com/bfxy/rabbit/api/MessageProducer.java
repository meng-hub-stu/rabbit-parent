package com.bfxy.rabbit.api;

import com.bfxy.rabbit.api.exception.MessageRunTimeException;

import java.util.*;
/**
 * 消息发送接口
 * @Author Mengdexin
 * @date 2022 -04 -17 -22:23
 */
public interface MessageProducer {

    /**
     * 	$send消息的发送 附带SendCallback回调执行响应的业务逻辑处理
     * @param message
     * @param sendCallback
     * @throws MessageRunTimeException
     */
    void send(Message message, SendCallback sendCallback) throws MessageRunTimeException;

    /** $send消息的发送
     * @param message 消息的发送
     * @throws MessageRunTimeException
     */
    void send(Message message) throws MessageRunTimeException;

    /**
     * 	$send 消息的批量发送
     * @param messages
     * @throws MessageRunTimeException
     */
    void send(List<Message> messages) throws MessageRunTimeException;

}
