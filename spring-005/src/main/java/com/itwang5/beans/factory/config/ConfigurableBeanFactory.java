package com.itwang5.beans.factory.config;

import com.itwang5.beans.factory.HierarchicalBeanFactory;
import com.itwang5.beans.factory.postProcessor.BeanPostProcessor;

/**
 * configurableBeanFactory
 *  Configuration interface to be implemented by most bean factories. Provides
 *  facilities to configure a bean factory, in addition to the bean factory
 *  client methods in the {}
 *  interface.
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addPostProcessor(BeanPostProcessor beanPostProcessor);
}
