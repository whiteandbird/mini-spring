package com.itwang6.util;

public class ClassUtils {
    public static ClassLoader getDefaultClassLoader(){
        ClassLoader cl = null;
        try{
            cl = Thread.currentThread().getContextClassLoader();
        }catch (Throwable e){

        }
        if(cl == null){
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    public static boolean isCglibProxyClass(Class<?> clazz){
        return clazz.getSimpleName().contains("&&");
    }
}
