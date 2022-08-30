package com.itwang6.beans.factory.aop;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:30  22:26
 */
public interface ClassFilter {
    boolean matchs(Class<?> clazz);
}
