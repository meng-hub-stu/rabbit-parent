package com.bfxy.rabbit.api;

/**
 * 回调函数的处理
 * @Author Mengdexin
 * @date 2022 -04 -17 -22:26
 */
public interface SendCallback {
    /**
     * 成功
     */
     void onSuccess();

    /**
     * 失败
     */
    void onFailure();

}
