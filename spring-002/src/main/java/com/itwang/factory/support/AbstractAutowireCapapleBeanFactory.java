package com.itwang.factory.support;

import com.itwang.BeansException;
import com.itwang.factory.config.BeanDefinition;

/**
 * 能够自动注入的bean工厂  创建的时候执行自动注入所以在这儿进行创建
 */
public abstract class AbstractAutowireCapapleBeanFactory extends AbstractBeanFactory{

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean;
        try{
            Class beanClass = beanDefinition.getBeanClass();
            bean = beanClass.newInstance();
        }catch (InstantiationException | IllegalAccessException e){
            throw new BeansException("创建bean失败");
        }
        // 暂时不判断bean是不是单例的
        addSingleton(beanName, bean);
        return bean;
    }
}
