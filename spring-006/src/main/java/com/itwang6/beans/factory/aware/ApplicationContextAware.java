package com.itwang6.beans.factory.aware;

import com.itwang6.beans.factory.application.ApplicationContext;

public interface ApplicationContextAware extends Aware{
    void setApplicationContext(ApplicationContext context);
}
