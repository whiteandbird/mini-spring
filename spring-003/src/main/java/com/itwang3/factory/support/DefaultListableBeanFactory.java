package com.itwang3.factory.support;

import com.itwang3.BeansException;
import com.itwang3.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultListableBeanFactory extends AbstractAutowireCapapleBeanFactory implements BeanDefinitionRegistry{

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanName == null){
            throw new BeansException("不存在beanDefintion ["+beanName+"] 的定义");
        }
        return beanDefinition;
    }
}
