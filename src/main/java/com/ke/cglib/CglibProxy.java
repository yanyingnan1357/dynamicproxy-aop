package com.ke.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther: yyn
 * CGLIB创建的动态代理对象比JDK创建的动态代理对象的性能更高，但是CGLIB创建代理对象时所花费的时间却比JDK多得多。
 * 所以对于单例的对象，因为无需频繁创建对象，用CGLIB合适，反之使用JDK方式要更为合适一些。
 * 同时由于CGLib由于是采用动态创建子类的方法，对于final修饰的方法无法进行代理
 *
 */
public class CglibProxy implements MethodInterceptor {

    @Override //继承MethodInterceptor接口重写intercept方法
    public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("这里可以做点啥......");
        Object result = methodProxy.invokeSuper(object, args);
//        Object result = method.invoke(object, args);  //报错
        System.out.println("这里可以做点啥......");
        return result;
    }

    public Object getInstance(Object target, Class[] args, Object[] argsValue) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create(args,argsValue);
    }

    public static void main(String[] args) {
        //创建一个实例对象，这个对象是被代理的对象
        Student zhangsan = new Student("张三");
        CglibProxy cglibProxy = new CglibProxy();
        Student instance = (Student) cglibProxy.getInstance(zhangsan, new Class[]{String.class}, new Object[]{zhangsan.getName()});
        instance.keepFit();
        instance.study();
    }

}
