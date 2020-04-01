package com.ke.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {
    public static void main(String[] args) {

        //创建一个实例对象，这个对象是被代理的对象
        Person zhangsan = new Student("张三");

        //创建一个代理对象stuProxy来代理zhangsan，代理对象的每个执行方法都会替换执行Invocation中的invoke方法
        Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("之前可以做点啥.......");

                Object result = method.invoke(zhangsan, args);

                System.out.println(result);

                System.out.println("之后可以做点啥.......");

                return proxy;
            }
        });

        //代理执行学习方法
        stuProxy.study().keepFit();
        //再增加执行代理方法只需要在这里调用即可，匿名内部类InvocationHandler不用做修改
//        stuProxy.keepFit();
    }
}

//1、相比于静态代理， 动态代理的优势在于可以很方便的对代理类的函数进行统一的处理，而不用修改每个代理类中的方法，即上面的匿名内部类InvocationHandler不用做修改。

//2、invoke方法的第一个参数proxy到底有什么作用？
//如果你的接口中有方法需要返回自身，如果在invoke中没有传入这个参数，将导致实例无法正常返回，
//这时proxy的用途就表现出来了。简单来说，这其实就是链式编程的一种应用实现。