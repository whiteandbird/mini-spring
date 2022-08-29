package com.itwang6.beans.factory.event;

import com.itwang6.beans.factory.BeanFactory;
import com.itwang6.beans.factory.aware.BeanFactoryAware;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static com.itwang6.util.ClassUtils.isCglibProxyClass;


@Slf4j
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    private Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> applicationListener) {
        this.applicationListeners.add((ApplicationListener<ApplicationEvent>) applicationListener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> applicationListener) {
        this.applicationListeners.remove(applicationListener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 不需要关注具体实现类
     * @param event
     * @return
     */
    public List<ApplicationListener> getApplicationListener(ApplicationEvent event){
        LinkedList<ApplicationListener> res  = new LinkedList<>();
        for(ApplicationListener<ApplicationEvent> listener : applicationListeners){
            if(ifSupportEvent(listener, event)){
                res.add(listener);
            }
        }
        return res;
    }

    private boolean ifSupportEvent(ApplicationListener<ApplicationEvent> listener, ApplicationEvent applicationEvent){
        Class<? extends ApplicationListener> listenerClass = listener.getClass();
        Class<? extends ApplicationEvent> eventClass = applicationEvent.getClass();

        // 如果是采用cglib生成的话 那么需要拿到对应的父类
        Class<?> targetClass = isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        // 获取直接由该实体类实现的接口
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String clazzName = actualTypeArgument.getTypeName();
        try{
            Class<?> aClass = Class.forName(clazzName);
            return aClass.isAssignableFrom(eventClass);

        }catch (ClassNotFoundException e){
            log.info("error");
        }

        return false;

    }


}
