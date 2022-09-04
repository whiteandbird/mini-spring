package com.itwang6.beans.factory.aop.advisor;

import com.itwang6.beans.factory.aop.PointCut;

public interface PointAdvisor extends Advisor {

    PointCut getPointCut();
}
