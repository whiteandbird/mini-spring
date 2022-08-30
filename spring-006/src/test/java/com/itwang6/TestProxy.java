package com.itwang6;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:30  21:26
 */
public class TestProxy {
    @Test
    public void testProxy(){
        Object targetObj = new UserService();
        Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler(){

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
    }

}
