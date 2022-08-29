package com.itwang6;

import com.itwang6.beans.factory.specialBean.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * 卧槽 其实这个就是相当于一个mapper 只声明了接口就行
 * 动态生成其实现类
 */
public class ProxyFactoryBean implements FactoryBean<IUserDao> {
    @Override
    public IUserDao getObject() {
        InvocationHandler handler = ((proxy, method, args) ->{
            if(method.getName().equals("toString")){
                return method.invoke(proxy, args);
            }
            Map<String,String> map = new HashMap<>();
            map.put("1","wang");
            map.put("2","duan");
            map.put("3","yue");
            System.out.println(this);
            return "你被代理了:"+map.get("1");
        });
        return (IUserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }
}
