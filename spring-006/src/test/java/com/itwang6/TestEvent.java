package com.itwang6;

import com.itwang6.beans.factory.event.ApplicationEvent;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:29  23:31
 */
public class TestEvent extends ApplicationEvent {

    private String msg;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public TestEvent(Object source) {
        super(source);
    }

    public TestEvent(Object source, String msg){
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
