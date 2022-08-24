package com.itwang3.factory.support;

import com.itwang3.BeansException;
import com.itwang3.factory.BeanFactory;
import com.itwang3.factory.config.BeanDefinition;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        // 如果池子里面有那么直接取出来
        return doGetBean(beanName, null);

    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }

    @SuppressWarnings("unchecked")
    protected <T> T doGetBean(final String beanName, final Object[] args){
        Object bean = getSingleton(beanName);
        if(bean != null){
            return (T) bean;
        }
        return (T)createBean(beanName, getBeanDefinition(beanName), args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;


    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

}
