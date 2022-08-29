package com.itwang6.beans.factory.specialBean;

public interface FactoryBean<T> {
    T getObject();

    boolean isSingleton();

    Class<?> getObjectType();
}
