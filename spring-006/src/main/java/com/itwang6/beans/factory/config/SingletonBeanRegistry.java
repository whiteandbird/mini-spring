package com.itwang6.beans.factory.config;

public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
