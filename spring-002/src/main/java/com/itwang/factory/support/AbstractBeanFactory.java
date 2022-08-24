package com.itwang.factory.support;

import com.itwang.BeansException;
import com.itwang.factory.BeanFactory;
import com.itwang.factory.config.BeanDefinition;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        // 如果池子里面有那么直接取出来
        Object singleBean = getSingleton(beanName);
        if(null != singleBean){
            return singleBean;
        }
        return createBean(beanName, getBeanDefinition(beanName));

    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;


    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

}
