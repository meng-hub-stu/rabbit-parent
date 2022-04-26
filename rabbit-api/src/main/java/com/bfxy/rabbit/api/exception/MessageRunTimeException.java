package com.bfxy.rabbit.api.exception;

/**
 * 运行异常
 * @Author Mengdexin
 * @date 2022 -04 -17 -22:18
 */
public class MessageRunTimeException extends RuntimeException{

    public MessageRunTimeException(){
        super();
    }

    public MessageRunTimeException(String message){
        super(message);
    }

    public MessageRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageRunTimeException(Throwable cause) {
        super(cause);
    }

}
