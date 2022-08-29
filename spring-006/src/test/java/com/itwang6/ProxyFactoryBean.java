package com.itwang6;

import com.itwang6.beans.factory.specialBean.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class ProxyFactoryBean implements FactoryBean<IUserDao> {
    @Override
    public IUserDao getObject() {
        InvocationHandler handler = ((proxy, method, args) ->{
            Map<String,String> map = new HashMap<>();
            map.put("1","wang");
            map.put("2","duan");
            map.put("3","yue");
            return "你被代理了:"+map.get(args[0].toString());
        });
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }
}
