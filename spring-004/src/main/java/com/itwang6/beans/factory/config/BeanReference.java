package com.itwang4.beans.factory.config;

/**
 * 用来引用其他bean的
 */
public class BeanReference {
    private final String beanName;

    public BeanReference(String beanName){
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
