package com.itwang6.beans.factory.config;

import com.itwang6.PropertyValues;
import lombok.Data;

@Data
@SuppressWarnings("rawtypes")
public class BeanDefinition {

    private Class beanClass;

    private String initializationMethodName;

    private String destroyMethodName;

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
