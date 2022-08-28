package com.itwang5.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import com.itwang5.beans.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    boolean containBeanDefinition(String beanName);

    String[] getBeanDefinitionNames();
}
