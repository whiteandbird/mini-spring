package com.itwang6.beans.factory.postProcessor;

public interface BeanPostProcessor {

    /**
     * 暂时未用
     * @param object
     * @param beanName
     * @return
     */
    Object postProcessorBeforeInitializtion(Object object, String beanName);

    /**
     * 用于bean对象创建完之后进行处理
     * @param object
     * @param beanName
     * @return
     */
    Object postProcessorAfterInitialization(Object object, String beanName);
}
