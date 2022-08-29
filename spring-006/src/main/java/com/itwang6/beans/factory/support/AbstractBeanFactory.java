package com.itwang6.beans.factory.support;

import com.itwang6.BeansException;
import com.itwang6.beans.factory.config.BeanDefinition;
import com.itwang6.beans.factory.config.ConfigurableBeanFactory;
import com.itwang6.beans.factory.postProcessor.BeanPostProcessor;
import com.itwang6.beans.factory.specialBean.FactoryBean;
import com.itwang6.beans.factory.specialBean.FactoryBeanRegistrySupport;
import com.itwang6.util.ClassUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

     private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    @Override
    public Object getBean(String beanName) throws BeansException {
        // 如果池子里面有那么直接取出来
        return doGetBean(beanName, null);

    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }


    @Override
    public <T> T getBean(String name, Class<T> requirdType) throws BeansException {
        // TODO 完善
        return (T) getBean(name);
    }

    @SuppressWarnings("unchecked")
    protected <T> T doGetBean(final String beanName, final Object[] args){
        Object bean = getSingleton(beanName);
        if(bean != null){
            return (T) getObjectForBeanInstance(bean, beanName);
        }
        bean = createBean(beanName, getBeanDefinition(beanName), args);
        return (T) getObjectForBeanInstance(bean, beanName);
    }


    @Override
    public void addPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    private Object getObjectForBeanInstance(Object bean, String beanName){
        if(!(bean instanceof FactoryBean)){
            return bean;
        }
        // 需要从 factoryBean 里面拿去东西
        // 拿到实际的对象
        Object cacheBean = getCacheObjectForFactoryBean(beanName);
        if(cacheBean == null){
            cacheBean = getObjectFromFactoryBean((FactoryBean<?>) bean, beanName);
        }
        return cacheBean;
    }


    public List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;


    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;


    public ClassLoader getClassLoader(){
        return this.classLoader;
    }
}
