package com.itwang6.beans.factory.event;

/**
 * 事件广播器
 */
public interface ApplicationEventMulticaster {


    void addApplicationListener(ApplicationListener<?> applicationListener);

    void removeApplicationListener(ApplicationListener<?> applicationListener);

    /**
     * 广播此类型的事件
     * @param applicationEvent
     */
    void multicastEvent(ApplicationEvent applicationEvent);

}
