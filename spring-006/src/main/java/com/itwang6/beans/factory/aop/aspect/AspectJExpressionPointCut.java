package com.itwang6.beans.factory.aop.aspect;

import com.itwang6.beans.factory.aop.ClassFilter;
import com.itwang6.beans.factory.aop.MethodMatcher;
import com.itwang6.beans.factory.aop.PointCut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:08:30  22:25
 */
public class AspectJExpressionPointCut implements PointCut, ClassFilter, MethodMatcher {


    private static final Set<PointcutPrimitive> SUPPORTED_PRIMTIVES = new HashSet<>();

    static {
        SUPPORTED_PRIMTIVES.add(PointcutPrimitive.EXECUTION);
    }

    private final PointcutExpression pointcutExpression;

    public AspectJExpressionPointCut(String expression){
        PointcutParser pointcutParser = PointcutParser.getPointcutParserSupportingSpecifiedPrimitivesAndUsingSpecifiedClassLoaderForResolution(SUPPORTED_PRIMTIVES, this.getClass().getClassLoader());
        pointcutExpression = pointcutParser.parsePointcutExpression(expression);
    }

    @Override
    public boolean matchs(Class<?> clazz) {
        return pointcutExpression.couldMatchJoinPointsInType(clazz);
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return pointcutExpression.matchesMethodExecution(method).alwaysMatches();
    }


    @Override
    public ClassFilter getClassFilter() {
        return this;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }
}
