package com.itwang4.beans.factory.config;

public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
