package com.itwang4.beans.factory.config;

import cn.hutool.core.bean.BeanUtil;
import com.itwang4.BeansException;
import com.itwang4.PropertyValue;
import com.itwang4.PropertyValues;
import com.itwang4.beans.factory.support.AbstractBeanFactory;
import com.itwang4.beans.factory.support.SimpleInstantiationStrategy;
import com.itwang4.beans.factory.support.InstantiationStrategy;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;

@Slf4j
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try{
            bean = createBeanInstance(beanName, beanDefinition, args);
            applyPropertyValues(beanName, bean, beanDefinition);
        }catch (Exception e){
            throw new BeansException("创建bean失败: "+beanName);
        }
        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args){
        Constructor constructorTOUse = null;
        Class beanClass = beanDefinition.getBeanClass();
        Constructor[] constructors = beanClass.getConstructors();
        for(Constructor con : constructors){
            if( args!= null && con.getParameterTypes().length == args.length){
                constructorTOUse = con;
                break;
            }
        }
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructorTOUse, args);
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    //bean 属性填充就是能注入一些东西
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        try{
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for(PropertyValue propertyValue : propertyValues.getPropertyValues()){
                // 填充bean
                String propertyName = propertyValue.getPropertyName();
                Object value = propertyValue.getPropertyValue();
                // 引用其他的bean
                if(value instanceof BeanReference){
                    value = getBean(((BeanReference)value).getBeanName());
                }
                BeanUtil.setFieldValue(bean, propertyName, value);
            }
        }catch (Exception e){
            log.info("填充属性异常 beanName: {}, {}",beanName,e);
        }
    }
}
