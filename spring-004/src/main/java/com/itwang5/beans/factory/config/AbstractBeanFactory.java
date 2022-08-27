package com.itwang4.beans.factory.config;

import com.itwang4.BeansException;
import com.itwang4.beans.factory.BeanFactory;
import com.itwang4.beans.factory.support.DefaultSingletonBeanRegistry;

public class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return null;
    }

    @Override
    public <T> T getBean(String name, Class<T> requirdType) throws BeansException {
        return null;
    }
}
