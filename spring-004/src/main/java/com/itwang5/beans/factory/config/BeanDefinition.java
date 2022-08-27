package com.itwang4.beans.factory.config;

import com.itwang4.PropertyValues;
import lombok.Data;

@Data
@SuppressWarnings("rawtypes")
public class BeanDefinition {

    private Class beanClass;

    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass){
        this.beanClass = beanClass;
        propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues){
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
    }
}
