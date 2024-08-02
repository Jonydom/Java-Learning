package org.example.learning.design.singleton;

public class SingletonDemo3 {
    private static volatile SingletonDemo3 instance;
    private SingletonDemo3(){}
    public static SingletonDemo3 getInstance() {
        if (instance == null) {
            synchronized (SingletonDemo3.class) {
                if (instance == null) {
                    instance = new SingletonDemo3();
                }
            }
        }
        return instance;
    }
}
