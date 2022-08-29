package com.itwang6;

import com.itwang6.beans.factory.event.ApplicationListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:29  23:37
 */
@Slf4j
public class TestListener implements ApplicationListener<TestEvent> {
    @Override
    public void onEvent(TestEvent event) {
        String msg = event.getMsg();
        log.info("接受到 自定义事件" +msg);
    }
}
