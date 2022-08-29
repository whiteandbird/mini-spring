package com.itwang6;

import com.itwang6.beans.factory.event.ApplicationListener;
import com.itwang6.beans.factory.event.ContextClosedEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:29  23:24
 */
@Slf4j
public class ContextClousedApplicationListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onEvent(ContextClosedEvent event) {
        log.info("接收到 close Event 事件");
    }
}
