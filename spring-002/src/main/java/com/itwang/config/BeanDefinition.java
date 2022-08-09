package com.itwang.config;

import lombok.Data;

@Data
@SuppressWarnings("rawtypes")
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
    }
}
