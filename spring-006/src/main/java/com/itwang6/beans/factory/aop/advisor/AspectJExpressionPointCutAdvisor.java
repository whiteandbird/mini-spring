package com.itwang6.beans.factory.aop.advisor;

import com.itwang6.beans.factory.aop.PointCut;
import com.itwang6.beans.factory.aop.aspect.AspectJExpressionPointCut;
import org.aopalliance.aop.Advice;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:09:04  7:29
 */
public class AspectJExpressionPointCutAdvisor implements PointAdvisor {

    private Advice advice;

    private AspectJExpressionPointCut aspectJExpressionPointCut;

    private String expression;

    public AspectJExpressionPointCutAdvisor(String expression){
        aspectJExpressionPointCut = new AspectJExpressionPointCut(expression);
    }

    public AspectJExpressionPointCutAdvisor(){}

    @Override
    public PointCut getPointCut() {
        if(null ==aspectJExpressionPointCut){
            aspectJExpressionPointCut = new AspectJExpressionPointCut(expression);
        }
        return aspectJExpressionPointCut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
