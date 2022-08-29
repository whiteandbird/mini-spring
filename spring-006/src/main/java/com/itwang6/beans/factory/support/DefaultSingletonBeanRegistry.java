package com.itwang6.beans.factory.support;

import com.itwang6.beans.factory.config.SingletonBeanRegistry;
import com.itwang6.beans.factory.lifescope.DisposableAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现singleBean的注册
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 单例池子 用来保存singleton scope的
     */
    private final Map<String, Object> singletonObjects = new HashMap<>();

    private final Map<String, DisposableAdapter> disposableAdapterMap = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    /**
     * 提供了放入池子的方法
     * @param beanName
     * @param singletonObject
     */
    public void addSingleton(String beanName,Object singletonObject){
        singletonObjects.put(beanName, singletonObject);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }

    public void registryDisposableBean(String beanName, DisposableAdapter disposableAdapter){
        disposableAdapterMap.put(beanName, disposableAdapter);
    }

    public void destroySingleBean(){
        disposableAdapterMap.forEach((name, disposableBean)->{
            disposableBean.destroy();
        });
    }
}
