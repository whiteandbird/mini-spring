package com.itwang4.beans.factory.support;

import com.itwang4.core.io.Resource;
import com.itwang4.core.io.ResourceLoader;

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

    void loadBeanDefinitions(Resource ...resources);
}
