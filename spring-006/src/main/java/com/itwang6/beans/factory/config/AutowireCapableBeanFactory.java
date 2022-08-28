package com.itwang6.beans.factory.config;

import com.itwang6.beans.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {

    Object applyBeanPostProcessorBeforeInitialzation(Object bean, String beanName);

    Object applyBeanPostProcessorAfterInitialization(Object bean, String beanName);
}
