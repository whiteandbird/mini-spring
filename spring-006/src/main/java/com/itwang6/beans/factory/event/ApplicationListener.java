package com.itwang6.beans.factory.event;

import java.util.EventListener;

/**
 * 需要监听哪个事件
 * @param <T>
 */
public interface ApplicationListener<T extends ApplicationEvent> extends EventListener {

    void onEvent(T event);
}
