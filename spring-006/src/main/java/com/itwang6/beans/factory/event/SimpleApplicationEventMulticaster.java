package com.itwang6.beans.factory.event;

import com.itwang6.beans.factory.BeanFactory;

import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:29  22:18
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory){
        setBeanFactory(beanFactory);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void multicastEvent(ApplicationEvent applicationEvent) {
        List<ApplicationListener> applicationListeners = getApplicationListener(applicationEvent);
        for(ApplicationListener applicationListener : applicationListeners){
            applicationListener.onEvent(applicationEvent);
        }

    }
}
