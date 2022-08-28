package com.itwang6.beans.factory;

import com.itwang6.BeansException;
import com.itwang6.beans.factory.config.AutowireCapableBeanFactory;
import com.itwang6.beans.factory.config.BeanDefinition;
import com.itwang6.beans.factory.config.ConfigurableBeanFactory;
import com.itwang6.beans.factory.postProcessor.BeanPostProcessor;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons();

    void addPostProcessor(BeanPostProcessor beanPostProcessor);
}
