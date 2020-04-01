package com.ke.cglib;

public class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public void study() {
        System.out.println(name + "最近学习成绩好");
    }

    public void keepFit() {
        System.out.println(name + "最近有坚持健身");
    }

    public String getName() {
        return name;
    }
}
