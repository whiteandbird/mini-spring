package com.itwang6.beans.factory.xml;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.itwang6.PropertyValue;
import com.itwang6.beans.factory.config.BeanDefinition;
import com.itwang6.beans.factory.config.BeanReference;
import com.itwang6.beans.factory.support.AbstractBeanDefinitionReader;
import com.itwang6.beans.factory.support.BeanDefinitionRegistry;
import com.itwang6.core.io.Resource;
import com.itwang6.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:26  23:33
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry){
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader){
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) {
        // 拿到数据流解析
        try{
            InputStream inputStream = resource.getInputStream();
            doLoadBeanDefinition(inputStream);
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            throw new BeanException("解析xml error");
        }
    }

    private void doLoadBeanDefinition(InputStream inputStream) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for(int x=0;x<childNodes.getLength();x++){
            if(!(childNodes.item(x) instanceof Element)){
                continue;
            }
            if(!childNodes.item(x).getNodeName().equals("bean"))continue;
            Element beanItem = (Element) childNodes.item(x);
            //bean的id
            String id = beanItem.getAttribute("id");
            String name = beanItem.getAttribute("name");
            String aClass = beanItem.getAttribute("class");

            Class<?> beanClass = Class.forName(aClass);

            // init Method or destroy method
            String destroy = beanItem.getAttribute("destroy");
            String init = beanItem.getAttribute("init");
            String beanScope = beanItem.getAttribute("scope");

            name = StrUtil.isEmpty(id) ? (StrUtil.isEmpty(name) ? StrUtil.lowerFirst(beanClass.getSimpleName()) :name ): id;
            BeanDefinition beanDefinition = new BeanDefinition(beanClass);

            if(StrUtil.isNotEmpty(destroy)){
                beanDefinition.setDestroyMethodName(destroy);
            }
            if(StrUtil.isNotEmpty(init)){
                beanDefinition.setInitializationMethodName(init);
            }

            // 设置单例
            if(StrUtil.isNotEmpty(beanScope)){
                beanDefinition.setScope(beanScope);
            }

            NodeList propertyNodeList = beanItem.getChildNodes();
            for(int propertyIndex=0; propertyIndex<propertyNodeList.getLength(); propertyIndex++){
                if(!(propertyNodeList.item(propertyIndex) instanceof Element)){
                    continue;
                }
                if(!(propertyNodeList.item(propertyIndex).getNodeName().equals("property")))continue;
                Element property = (Element) propertyNodeList.item(propertyIndex);
                String propertyName = property.getAttribute("name");
                String propertyValue = property.getAttribute("value");
                String textContent = property.getTextContent();
                propertyValue = StrUtil.isEmpty(propertyValue) ? textContent : propertyValue;
                String ref = property.getAttribute("ref");
                Object value = StrUtil.isNotEmpty(propertyValue) ? propertyValue :  new BeanReference(ref);
                beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(propertyName, value));

            }
            if(getRegistry().containBeanDefinition(name)) throw new BeanException("重复定义"+name);
            getRegistry().registerBeanDefinition(name, beanDefinition);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) {
        for(Resource resource : resources){
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String... locations) {
        for(String location : locations){
            loadBeanDefinitions(location);
        }
    }
}
