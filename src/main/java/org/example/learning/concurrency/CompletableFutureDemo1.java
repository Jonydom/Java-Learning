package org.example.learning.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture实战
 * 参考: https://cloud.tencent.com/developer/article/2292270
 */
public class CompletableFutureDemo1 {
    public static void main(String[] args) {
        try {
            CompletableFutureDemo1.testSupplyAsync();
            CompletableFutureDemo1.testRunAsync();
            CompletableFutureDemo1.testThenApply();
            CompletableFutureDemo1.testThenAccept();
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    /**
     * 测试supplyAsync方法，有返回值，默认使用ForkJoinPoll.commonPool()作为线程池
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void testSupplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return "hello";
        });
        System.out.println("future = " + future.get());
    }

    /**
     * 测试runAsync方法，返回值，get()返回值为null
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void testRunAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("runAsync方法正在执行...");
        });
        System.out.println("future = " + future.get());
    }

    /**
     * 测试thenApply方法，组合调度，能拿到上一步执行的结果，并且当前执行完有任务返回值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void testThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("suppleAsync方法执行...");
            return "hello";
        }).thenApply((res) -> {
            System.out.println("thenApply方法执行...得到上一步的执行结果：" + res);
            return res + " world";
        });
        System.out.println("future = " + future.get());
    }

    /**
     * 测试thenAccept方法，组合调度，能拿到上一步执行的结果，并且当前执行完没有任务返回值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void testThenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync方法执行...");
            return "hello";
        }).thenAccept((res) -> {
            System.out.println("thenAccept方法执行...得到上一步的执行结果：" + res);
        });
        System.out.println("future = " + future.get());
    }
}
