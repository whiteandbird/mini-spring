package com.itwang.spring4;

import com.itwang4.beans.factory.support.DefaultListableBeanFactory;
import com.itwang4.beans.factory.xml.XmlBeanDefinitionReader;
import com.itwang4.core.io.DefaultResourceLoader;
import com.itwang4.core.io.Resource;
import com.itwang4.core.io.ResourceLoader;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:27  0:09
 */
public class ApiTest {


    public static String read(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int read = 0;
        StringBuilder builder = new StringBuilder();
        while ((read = inputStream.read(buffer)) != -1){
            builder.append(new String(buffer, 0, read));
        }
        return builder.toString();
    }

    @Test
    public void testFileResource() throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("src/main/resources/testBean.xml");
        InputStream inputStream = resource.getInputStream();
        System.out.println(read(inputStream));
    }

    @Test
    public void testUrl() throws IOException{
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("http://www.baidu.com");
        System.out.println(read(resource.getInputStream()));
    }

    @Test
    public void test(){
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        // xmlBeanDefinitionReader需要BeanDefinitionRegistery 而 defualtLisstAbleBeanFactory就是这个类型的
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);

        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:testBean.xml");

        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");
        userService.say();


    }
}
