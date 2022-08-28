package com.itwang5.beans.factory.support;

import com.itwang5.BeansException;
import com.itwang5.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

// 初始化策略
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException;
}
