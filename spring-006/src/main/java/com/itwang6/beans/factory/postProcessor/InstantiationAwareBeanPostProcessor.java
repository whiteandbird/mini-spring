package com.itwang6.beans.factory.postProcessor;

public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /**
     * 在bean对象执行初始化之前 执行此方法
     * @param beanClazz
     * @param beanName
     * @return
     */
    Object postProcessBeforeInstantiation(Class<?> beanClazz, String beanName);
}
