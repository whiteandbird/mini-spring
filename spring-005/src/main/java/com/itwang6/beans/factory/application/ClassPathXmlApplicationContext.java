package com.itwang5.beans.factory.application;

import com.itwang5.beans.factory.ConfigurableListableBeanFactory;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:27  12:24
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] locations;

    public ClassPathXmlApplicationContext(String location){
        this(new String[]{location});
    }

    public ClassPathXmlApplicationContext(String[] locations){
        this.locations = locations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return locations;
    }
}
