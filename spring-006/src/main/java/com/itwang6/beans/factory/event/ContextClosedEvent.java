package com.itwang6.beans.factory.event;

import com.itwang6.beans.factory.application.ApplicationContext;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ContextClosedEvent extends ApplicationEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }

    static class TestEvent extends ApplicationEvent{

        /**
         * Constructs a prototypical Event.
         *
         * @param source The object on which the Event initially occurred.
         * @throws IllegalArgumentException if source is null.
         */
        public TestEvent(String source) {
            super(source);
        }
    }


}
