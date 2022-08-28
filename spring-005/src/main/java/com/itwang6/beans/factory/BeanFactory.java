package com.itwang5.beans.factory;

import com.itwang5.BeansException;

public interface BeanFactory {
    Object getBean(String beanName);

    Object getBean(String beanName, Object... args);

    <T> T getBean(String name, Class<T> requirdType) throws BeansException;
}
