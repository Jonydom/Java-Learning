package org.example.learning.thread;

/**
 * 交替打印奇偶数
 * 通过 synchronized 同步两个方法，每次只能有一个线程进入，每打印一个数，就释放锁，另一个线程进入，拿到锁，打印，唤醒另一个线程，然后挂起自己。循环反复，实现了一个最基本的打印功能。
 */
public class ThreadPrintDemo1 {
    public static void main(String[] args) {
        ThreadPrintDemo1 threadPrintDemo1 = new ThreadPrintDemo1();
        Thread thread1 = new Thread(threadPrintDemo1::print1, "thread-1");
        Thread thread2 = new Thread(threadPrintDemo1::print2, "thread-2");
        thread1.start();
        thread2.start();
    }

    public synchronized void print1() {
        for (int i = 0; i <= 100; i+=2) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            this.notify();
            try {
                this.wait();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // No
            }
        }
    }

    public synchronized void print2() {
        for (int i = 1; i <= 100; i+=2) {
            System.out.println(Thread.currentThread().getName() + " : " + i);
            this.notify();
            try {
                this.wait();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // No
            }
        }
    }
}
