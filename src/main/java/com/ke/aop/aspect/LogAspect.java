package com.ke.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Order(1)
public class LogAspect {

    @Pointcut("execution(public * com.ke.aop.controller.*.*(..))")
    public void webLog() {
    }

    //环绕增强
    @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) {
        System.out.println("LogAspect-方法环绕增强.......");
        try {
            Object o = pjp.proceed();
            System.out.println("LogAspect-方法环绕增强.......，取到方法执行后的返回结果是 :" + o);
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        //接收到请求后，打印记录出请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        System.out.println("URL : " + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("IP : " + request.getRemoteAddr());
        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    @After("webLog()")//后置通知,final增强，不管是抛出异常或者正常退出都会执行
    public void after(JoinPoint jp) {
        System.out.println("方法执行后.......");
    }

    @AfterReturning(returning = "result", pointcut = "webLog()")//方法执行不管是抛出异常或者正常退出都会执行
    public void doAfterReturning(Object result) throws Throwable {
        System.out.println("请求处理完成后.......返回值: " + result);
    }

    @AfterThrowing("webLog()")//后置异常通知
    public void throwz(JoinPoint jp) {
        System.out.println("方法执行异常了.......");
    }

}
