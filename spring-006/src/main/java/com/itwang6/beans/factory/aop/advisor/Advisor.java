package com.itwang6.beans.factory.aop.advisor;

import org.aopalliance.aop.Advice;

/**
 * 访问者
 */
public interface Advisor {
    Advice getAdvice();
}
