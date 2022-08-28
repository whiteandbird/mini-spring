package com.itwang6.beans.factory.support;

import com.itwang6.BeansException;
import com.itwang6.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

// 初始化策略
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException;
}
