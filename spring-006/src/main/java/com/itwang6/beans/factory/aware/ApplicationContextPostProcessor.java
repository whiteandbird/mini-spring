package com.itwang6.beans.factory.aware;

import com.itwang6.beans.factory.application.ApplicationContext;
import com.itwang6.beans.factory.postProcessor.BeanPostProcessor;

/**
 * 采用postProccessor的方法注入Aware
 */
public class ApplicationContextPostProcessor implements BeanPostProcessor {

    public ApplicationContext context;

    public ApplicationContextPostProcessor(ApplicationContext context){
        this.context = context;
    }

    @Override
    public Object postProcessorBeforeInitializtion(Object object, String beanName) {
        if(object instanceof ApplicationContextAware){
            ((ApplicationContextAware)object).setApplicationContext(context);
        }
        return object;
    }

    @Override
    public Object postProcessorAfterInitialization(Object object, String beanName) {
        return null;
    }
}
