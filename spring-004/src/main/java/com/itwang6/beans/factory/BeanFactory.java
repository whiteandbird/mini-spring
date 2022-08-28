package com.itwang4.beans.factory;

import com.itwang4.BeansException;

public interface BeanFactory {
    Object getBean(String beanName);

    Object getBean(String beanName, Object ...args);

    <T> T getBean(String name, Class<T> requirdType) throws BeansException;
}
