package com.itwang5;

import com.itwang5.beans.factory.postProcessor.BeanPostProcessor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:27  15:24
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
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
}
