package com.itwang3.factory;

public interface BeanFactory {
    Object getBean(String beanName);

    Object getBean(String beanName, Object ...args);
}
