package com.itwang6.beans.factory.support;

import com.itwang6.BeansException;
import com.itwang6.beans.factory.config.BeanDefinition;
import com.itwang6.beans.factory.config.ConfigurableBeanFactory;
import com.itwang6.beans.factory.postProcessor.BeanPostProcessor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

     private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String beanName) throws BeansException {
        // 如果池子里面有那么直接取出来
        return doGetBean(beanName, null);

    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }


    @Override
    public <T> T getBean(String name, Class<T> requirdType) throws BeansException {
        // TODO 完善
        return (T) getBean(name);
    }

    @SuppressWarnings("unchecked")
    protected <T> T doGetBean(final String beanName, final Object[] args){
        Object bean = getSingleton(beanName);
        if(bean != null){
            return (T) bean;
        }
        return (T)createBean(beanName, getBeanDefinition(beanName), args);
    }


    @Override
    public void addPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }


    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;


    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

}
