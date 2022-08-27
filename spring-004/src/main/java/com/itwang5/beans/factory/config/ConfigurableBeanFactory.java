package com.itwang4.beans.factory.config;

import com.itwang4.beans.factory.HierarchicalBeanFactory;

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
}
