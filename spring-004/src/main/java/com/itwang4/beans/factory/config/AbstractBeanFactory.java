package com.itwang4.beans.factory.config;

import com.itwang4.beans.factory.BeanFactory;

public class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return null;
    }
}
