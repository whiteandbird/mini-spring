package com.itwang3.factory.support;

import com.itwang3.BeansException;
import com.itwang3.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

// 初始化策略
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException;
}
