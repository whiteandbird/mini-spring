package com.itwang4.beans.factory.support;

import com.itwang4.core.io.DefaultResourceLoader;
import com.itwang4.core.io.ResourceLoader;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:26  22:29
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;


    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader){
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry){
        this.registry = registry;
        this.resourceLoader = new DefaultResourceLoader();
    }


    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
