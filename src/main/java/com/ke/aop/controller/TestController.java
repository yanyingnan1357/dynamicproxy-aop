package com.ke.aop.controller;


import com.ke.aop.annotation.UserCache;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test1")
    public Object test1() {
        return "test1 controller";
    }

    @RequestMapping("/test2")
    @UserCache(desc = "test2")
    public Object test2() {
        return "test2 controller";
    }

    @RequestMapping("/testError")
    public Object error() {
        return 1 / 0;
    }
}


//aop注解源头@EnableAspectJAutoProxy
//import了 AspectJAutoProxyRegistrar组件 implements 了 ImportBeanDefinitionRegistrar  给容器中注册bean
//什么bean呢？AnnotationAwareAspectJAutoProxyCreator（aspectj自动代理创建器）
//因此只需研究透AnnotationAwareAspectJAutoProxyCreator组件即可（何时工作？做了什么？）

/**
 * AnnotationAwareAspectJAutoProxyCreator
 * AspectJAwareAdvisorAutoProxyCreator
 * AbstractAdvisorAutoProxyCreator
 * AbstractAutoProxyCreator
 * implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 * （重点关注后置处理器 即bean初始化前后所做的工作   以及自动装配bean）
 */