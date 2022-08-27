package com.itwang5.beans.factory;

import com.itwang5.BeansException;

import java.util.Map;

public interface ListableBeanFactory extends BeanFactory{

    /**
     * 获取该类型的bean
     * @param tClass
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> tClass) throws BeansException;


    String[] getBeanDefinitionNames();
}
