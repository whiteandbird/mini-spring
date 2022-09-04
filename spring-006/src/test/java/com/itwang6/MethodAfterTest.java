package com.itwang6;

import com.itwang6.beans.factory.aop.advise.MethodAfterAdvice;

import java.lang.reflect.Method;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:09:04  13:29
 */
public class MethodAfterTest implements MethodAfterAdvice {
    @Override
    public void after(Object target, Method method, Object[] args) {
        System.out.println("========this is after 方法======");
    }
}
