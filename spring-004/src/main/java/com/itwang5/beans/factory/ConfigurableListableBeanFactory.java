package com.itwang4.beans.factory;

import com.itwang4.BeansException;
import com.itwang4.beans.factory.config.ConfigurableBeanFactory;
import com.itwang4.beans.factory.config.AutowireCapableBeanFactory;
import com.itwang4.beans.factory.config.BeanDefinition;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
