package org.example.learning.thread;

/**
 * @author Jonydom
 * @description 交替打印123，使用volatile实现
 * @date 2024-09-10 16:32
 */
public class ThreadPrintDemo2 {
    private volatile int flag = 1;
    public void printA() {
        while (true) {
            if (flag == 1) {
                synchronized (this) {
                    if (flag == 1) {
                        System.out.println(Thread.currentThread().getName() + " : 1");
                        flag = 2;
                    }
                }
            }
        }
    }
    public void printB() {
        while (true) {
            if (flag == 2) {
                synchronized (this) {
                    if (flag == 2) {
                        System.out.println(Thread.currentThread().getName() + " : 2");
                        flag = 3;
                    }
                }
            }
        }
    }
    public void printC() {
        while (true) {
            if (flag == 3) {
                synchronized (this) {
                    if (flag == 3) {
                        System.out.println(Thread.currentThread().getName() + " : 3");
                        flag = 1;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadPrintDemo2 demo2 = new ThreadPrintDemo2();
        new Thread(demo2::printA, "thread-A").start();
        new Thread(demo2::printB, "thread-B").start();
        new Thread(demo2::printC, "thread-C").start();
    }

}
