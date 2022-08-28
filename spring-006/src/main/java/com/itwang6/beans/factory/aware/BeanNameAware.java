package com.itwang6.beans.factory.aware;

public interface BeanNameAware extends Aware {

    void setBeanName(String beanName);
}
