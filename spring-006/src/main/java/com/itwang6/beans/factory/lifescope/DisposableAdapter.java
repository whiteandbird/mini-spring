package com.itwang6.beans.factory.lifescope;

import cn.hutool.core.util.StrUtil;
import com.itwang6.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:27  21:20
 */
public class DisposableAdapter implements DisposableBean {

    private String beanName;

    private String destroyMethodName;

    private Object bean;

    public DisposableAdapter(Object bean, String beanName, BeanDefinition beanDefinition){
        this.bean  = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() {
        if(bean instanceof DisposableBean){
            ((DisposableBean)bean).destroy();
        }
        try{
            if(StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof  DisposableBean ) ){
                Method declaredMethod = bean.getClass().getDeclaredMethod(destroyMethodName);
                declaredMethod.invoke(bean);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
