package com.itwang4.beans.factory.support;

import com.itwang4.BeansException;
import com.itwang4.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 能够自动注入的bean工厂  创建的时候执行自动注入所以在这儿进行创建
 */
public abstract class AbstractAutowireCapapleBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition,Object[] args) throws BeansException {
        Object bean;
        try{
            bean = createBeanInstance(beanDefinition, beanName, args);
        }catch (Exception e){
            throw new BeansException("创建bean失败", e);
        }
        // 暂时不判断bean是不是单例的
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args){
        Constructor constructorTOUse = null;
        Class beanClass = beanDefinition.getBeanClass();
        Constructor[] constructors = beanClass.getConstructors();
        for(Constructor con : constructors){
            if(null != args && con.getParameterTypes().length ==  args.length){
                constructorTOUse = con;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructorTOUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy){
        this.instantiationStrategy = instantiationStrategy;
    }
}
