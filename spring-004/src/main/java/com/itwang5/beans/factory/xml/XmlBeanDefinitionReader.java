package com.itwang4.beans.factory.xml;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.itwang4.PropertyValue;
import com.itwang4.beans.factory.config.BeanDefinition;
import com.itwang4.beans.factory.config.BeanReference;
import com.itwang4.beans.factory.support.AbstractBeanDefinitionReader;
import com.itwang4.beans.factory.support.BeanDefinitionRegistry;
import com.itwang4.core.io.Resource;
import com.itwang4.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
            Element beanItem = (Element) childNodes.item(x);
            //bean的id
            String id = beanItem.getAttribute("id");
            String name = beanItem.getAttribute("name");
            String aClass = beanItem.getAttribute("class");

            Class<?> beanClass = Class.forName(aClass);

            name = StrUtil.isEmpty(id) ? StrUtil.isEmpty(name) ? StrUtil.lowerFirst(beanClass.getSimpleName()) :name : id;
            BeanDefinition beanDefinition = new BeanDefinition(beanClass);
            NodeList propertyNodeList = beanItem.getChildNodes();
            for(int propertyIndex=0; propertyIndex<propertyNodeList.getLength(); propertyIndex++){
                if(!(propertyNodeList.item(propertyIndex) instanceof Element)){
                    continue;
                }
                if(!(propertyNodeList.item(propertyIndex).getNodeName().equals("property")))continue;
                Element property = (Element) propertyNodeList.item(propertyIndex);
                String propertyName = property.getAttribute("name");
                String propertyValue = property.getAttribute("value");
                String ref = property.getAttribute("ref");
                Object value = StrUtil.isNotEmpty(propertyValue) ? propertyValue :  new BeanReference(ref);
                beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(propertyName, value));

            }
            getRegistry().registerBeanDefinition(name, beanDefinition);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) {
        for(Resource resource : resources){
            loadBeanDefinitions(resource);
        }
    }
}
