package com.itwang6.beans.factory.specialBean;

import com.itwang6.BeansException;
import com.itwang6.beans.factory.support.DefaultSingletonBeanRegistry;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    // 又是一个单例缓存
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCacheObjectForFactoryBean(String beanName){
        Object o = this.factoryBeanObjectCache.get(beanName);
        return o;
    }

    protected Object getObjectFromFactoryBean(FactoryBean factoryBean, String beanName){
        if(factoryBean.isSingleton()){
            Object cacheObjectForFactoryBean = this.getCacheObjectForFactoryBean(beanName);
            if(cacheObjectForFactoryBean == null){
                cacheObjectForFactoryBean = doGetObjectFromFactoryBean(factoryBean, beanName);
                this.factoryBeanObjectCache.put(beanName, cacheObjectForFactoryBean);
            }
            return cacheObjectForFactoryBean;
        }else{
            return doGetObjectFromFactoryBean(factoryBean, beanName);
        }
    }

    // 创建factoryBean
    private Object doGetObjectFromFactoryBean(final FactoryBean factoryBean, final String beanName){
        try{
            System.out.println("======创建factoryBean===="+beanName);
            return factoryBean.getObject();
        }catch (Exception e){
            e.printStackTrace();
            log.info("获取factoryBean 失败: {}",beanName);
            throw new BeansException("获取factoryBean失败");
        }
    }


}
