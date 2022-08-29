package com.itwang6;

import com.itwang6.beans.factory.application.ApplicationContext;
import com.itwang6.beans.factory.aware.ApplicationContextAware;
import com.itwang6.beans.factory.event.AbstractApplicationEventMulticaster;
import com.itwang6.beans.factory.event.ApplicationEvent;
import com.itwang6.beans.factory.event.ApplicationListener;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:29  23:43
 */
@Slf4j
public class UserCustomMulticaster extends AbstractApplicationEventMulticaster implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) {
        this.applicationContext = context;
    }

    @Override
    public void multicastEvent(ApplicationEvent applicationEvent) {
        log.error("实现自定义·      事件发布器");
        List<ApplicationListener> applicationListener = getApplicationListener(applicationEvent);
        for(ApplicationListener ls : applicationListener){
            ls.onEvent(applicationEvent);
        }
    }
}
