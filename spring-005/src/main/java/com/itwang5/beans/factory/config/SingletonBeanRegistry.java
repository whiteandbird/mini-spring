package com.itwang5.beans.factory.config;

public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);
}
