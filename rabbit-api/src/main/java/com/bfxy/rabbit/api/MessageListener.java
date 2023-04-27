package com.bfxy.rabbit.api;

/**
 * 消息监听接口
 * @Author Mengdexin
 * @date 2022 -04 -17 -22:22
 */
public interface MessageListener {
    /**
     * 发送消息
     * @param message 消息内容
     */
    void onMessage(Message message);

}
