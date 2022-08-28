package com.itwang6;

import com.itwang6.beans.factory.application.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:27  12:31
 */
public class ApiTest {

    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:testBean.xml");
        context.registerShutHook();
        UserService userService = context.getBean("userService", UserService.class);
        userService.sayHello();





    }
}
