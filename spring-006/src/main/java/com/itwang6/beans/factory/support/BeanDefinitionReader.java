package com.itwang6.beans.factory.support;

import com.itwang6.core.io.Resource;
import com.itwang6.core.io.ResourceLoader;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:26  21:47
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(String location);

    void loadBeanDefinitions(Resource resource);

    void loadBeanDefinitions(Resource... resources);

    void loadBeanDefinitions(String... locations);
}
