package com.config;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import java.lang.reflect.Method;

/**
 * Aop配置类
 */
@Configuration
@ImportResource(locations={"classpath:/springboot-config.xml"})
public class AopAdvice implements MethodBeforeAdvice, AfterReturningAdvice {
    /**
     * 前置通知
     * @param method
     * @param objects
     * @param o
     * @throws Throwable
     */
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        String sql = "";
        System.out.println("前置通知");
    }

    /**
     * 后置通知
     * @param o
     * @param method
     * @param objects
     * @param o1
     * @throws Throwable
     */
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("后置通知");
    }
}
