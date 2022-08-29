package com.itwang6;

import com.itwang6.beans.factory.BeanFactory;
import com.itwang6.beans.factory.application.ApplicationContext;
import com.itwang6.beans.factory.aware.ApplicationContextAware;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class MyAware implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) {
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        log.info("llllllllllllllllll==============acquire bean Definition====================");
        for(String beanName : beanDefinitionNames){
            log.error(beanName);
        }
    }
}
