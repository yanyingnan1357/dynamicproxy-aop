package com.ke.aop.aspect;

import com.ke.aop.annotation.UserCache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class CacheAspect {

//    @Pointcut(value = "@annotation(UserCache)")
//    public void access() {
//
//    }
//
//    @Before("access()")
//    public void deBefore(JoinPoint joinPoint) throws Throwable {
//        System.out.println("second before");
//    }

    @Around("@annotation(userCache)")
    public Object around(ProceedingJoinPoint pjp, UserCache userCache) {

        System.out.println("CacheAspect-方法环绕增强......." + userCache.desc());//获取注解里的值
        try {
            Object o = pjp.proceed();
            System.out.println("CacheAspect-方法环绕增强.......，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}
