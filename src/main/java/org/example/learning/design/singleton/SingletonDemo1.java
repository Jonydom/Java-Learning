package org.example.learning.design.singleton;

public class SingletonDemo1 {
    private static final SingletonDemo1 instance = new SingletonDemo1();
    private SingletonDemo1(){}
    public static SingletonDemo1 getInstance() {
        return instance;
    }
}
