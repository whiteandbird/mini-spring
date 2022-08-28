package com.itwang6.beans.factory.support;

import com.itwang6.BeansException;
import com.itwang6.beans.factory.ConfigurableListableBeanFactory;
import com.itwang6.beans.factory.config.AbstractAutowireCapableBeanFactory;
import com.itwang6.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanName == null){
            throw new BeansException("不存在beanDefintion ["+beanName+"] 的定义");
        }
        return beanDefinition;
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> tClass) throws BeansException {
        Map<String, T> resultMap = new HashMap<>();
        beanDefinitionMap.forEach((beanName, definition)->{
            if(tClass.isAssignableFrom(definition.getBeanClass())){
                resultMap.put(beanName, getBean(beanName, tClass));
            }
        });
        return resultMap;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public boolean containBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public void preInstantiateSingletons() {
        beanDefinitionMap.keySet().forEach(this::getBean);
    }

    @Override
    public void destroySingletons() {
        destroySingleBean();
    }
}
