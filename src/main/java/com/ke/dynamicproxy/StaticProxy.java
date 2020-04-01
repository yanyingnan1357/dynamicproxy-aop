package com.ke.dynamicproxy;

public class StaticProxy implements Person {

    Student stu;

    public StaticProxy(Person stu) {
        if (stu.getClass() == Student.class) {
            this.stu = (Student) stu;
        }
    }

    @Override
    public Person study() {

        System.out.println("这里可以做点啥.......");
        stu.study();
        System.out.println("这里可以做点啥.......");
        return this;
    }

    @Override
    public Person keepFit() {
        System.out.println("这里可以做点啥.......");
        stu.keepFit();
        System.out.println("这里可以做点啥.......");
        return this;
    }


    public static void main(String[] args) {

        Person zhangsan = new Student("张三");

        Person stuProxy = new StaticProxy(zhangsan);

        stuProxy.study();

        stuProxy.keepFit();
    }
}
