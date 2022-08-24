package com.itwang.factory.config;

public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
