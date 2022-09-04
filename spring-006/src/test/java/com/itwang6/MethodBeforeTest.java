package com.itwang6;

import com.itwang6.beans.factory.aop.advise.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:09:04  13:28
 */
public class MethodBeforeTest implements MethodBeforeAdvice {
    @Override
    public void before(Object target, Method method, Object[] args) {
        System.out.println("这是before方法");
        System.out.println("===="+method.getName()+"=======");
        System.out.println("=======================");
    }
}
