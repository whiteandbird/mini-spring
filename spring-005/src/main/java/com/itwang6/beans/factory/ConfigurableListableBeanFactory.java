package com.itwang5.beans.factory;

import com.itwang5.BeansException;
import com.itwang5.beans.factory.config.AutowireCapableBeanFactory;
import com.itwang5.beans.factory.config.BeanDefinition;
import com.itwang5.beans.factory.config.ConfigurableBeanFactory;
import com.itwang5.beans.factory.postProcessor.BeanPostProcessor;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons();

    void addPostProcessor(BeanPostProcessor beanPostProcessor);
}
