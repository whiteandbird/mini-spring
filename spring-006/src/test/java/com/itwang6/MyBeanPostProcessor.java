package com.itwang6;

import com.itwang6.beans.factory.lifescope.DisposableBean;
import com.itwang6.beans.factory.postProcessor.BeanPostProcessor;

import java.lang.reflect.Field;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:27  15:24
 */
public class MyBeanPostProcessor implements BeanPostProcessor, DisposableBean {
    @Override
    public Object postProcessorBeforeInitializtion(Object object, String beanName) {
        try{
            if(beanName.equals("userService")){
                Field name = UserService.class.getDeclaredField("name");
                name.setAccessible(true);
                name.set(object, "hello world");
            }
        }catch (Exception e){

        }
        return object;
    }

    @Override
    public Object postProcessorAfterInitialization(Object object, String beanName) {
        return null;
    }

    public void test(){
        System.out.println("-----------------test===================destroy===============");
    }

    @Override
    public void destroy() {
        System.out.println("==========disposable bean============");
        System.out.println("=========this is destroy test if this bean is dead then dead=============");
    }
}
