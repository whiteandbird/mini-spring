package com.itwang6.beans.factory.aop;

import java.lang.reflect.Method;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:30  22:26
 */
public interface MethodMatcher {
    boolean matches(Method method, Class<?> targetClass);
}
