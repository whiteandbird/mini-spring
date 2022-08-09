package com.itwang;

import com.itwang.springframework.BeanDefinition;
import com.itwang.springframework.BeanFactory;
import org.junit.Test;

public class ApiTest {
    @Test
    public void test(){
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(new UserService()));

        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryInfo();
    }
}
