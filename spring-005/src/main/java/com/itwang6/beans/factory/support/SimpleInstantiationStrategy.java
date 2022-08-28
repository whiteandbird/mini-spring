package com.itwang5.beans.factory.support;

import com.itwang5.BeansException;
import com.itwang5.beans.factory.config.BeanDefinition;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 建单初始化策略
 */
@Slf4j
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException {
        log.info("通过simple策略创建实例");
        Class beanClass = beanDefinition.getBeanClass();
        try{
            if(constructor != null){
                return beanClass.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            }else{
                return beanClass.getDeclaredConstructor().newInstance();
            }
        }catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            throw new BeansException("beanName ["+beanName+"] 初始化失败",e);
        }
    }
}
