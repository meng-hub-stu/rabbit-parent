package com.bfxy.rabbit.api.exception;

/**
 * @Author Mengdexin
 * @date 2022 -04 -17 -22:21
 */
public class MessageException extends Exception{

    public MessageException(){
        super();
    }

    public MessageException(String message){
        super(message);
    }

    public MessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageException(Throwable cause) {
        super(cause);
    }

}
