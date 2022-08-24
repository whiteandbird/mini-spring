package com.itwang;

public class BeansException extends RuntimeException{
    private String msg;

    public BeansException(String msg){
        super(msg);
        this.msg = msg;
    }
}
