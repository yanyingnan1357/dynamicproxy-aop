package com.ke.dynamicproxy;

public class Student implements Person {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public Person study() {
        System.out.println(name + "最近学习成绩好");
        return this;
    }

    @Override
    public Person keepFit() {
        System.out.println(name + "最近有坚持健身");
        return this;
    }
}
