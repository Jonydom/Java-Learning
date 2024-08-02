package org.example.learning.concurrency;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo3 {
    public static void main(String[] args) {
        // testAllOf();
        try {
            testAnyOf();
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    /**
     * 测试 allOf 方法，所有任务执行完再结束
     * 没有返回值
     */
    public static void testAllOf() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("future1执行完成");
            return "future1执行完成";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future2执行完成");
            return "future2执行完成";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future3执行完成");
            return "future3执行完成";
        });

        CompletableFuture<Void> all = CompletableFuture.allOf(future1, future2, future3);

        // 阻塞，知道所有任务结束
        System.out.println(Thread.currentThread().getName() + ":" + LocalDateTime.now() + ":阻塞");
        // 调用 join 方法等待全部方法完成
        all.join();

        if (all.isDone()) {
            System.out.println("全部任务执行完成");
        }
        System.out.println(Thread.currentThread().getName() + ":" + LocalDateTime.now() + ":阻塞结束");
    }

    /**
     * 测试 anyOf 方法，只要有一个任务执行完就结束
     * 返回值类型 CompletableFuture<Object>
     */
    public static void testAnyOf() throws Exception{
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1执行完成");
            return "future1执行完成";
        });


        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future2执行完成");
            return "future2执行完成";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future3执行完成");
            return "future3执行完成";
        });

        CompletableFuture<Object> any = CompletableFuture.anyOf(future1, future2, future3);

        //阻塞，最快任务执行完成 任务结束。
        System.out.println(Thread.currentThread().getName() + ":" + LocalDateTime.now() + ":阻塞");
        //调用join方法等待最快的一个任务执行
        any.join();

        if (any.isDone()) {
            //一个需要耗时2秒，一个需要耗时3秒，一个耗时5秒 当最短的完成则会结束
            System.out.println(any.get()+"任务执行完成");
        }
        System.out.println(Thread.currentThread().getName() + ":" + LocalDateTime.now() + ":阻塞结束");
    }
}
