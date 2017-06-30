package com.github.mpusher.learn.jdk8_features;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by Sean.liu on 2017/6/30.
 * link:http://www.voidcn.com/blog/zjysource/article/p-6373919.html
 */
public class CompleteFutureDemo {

    private static Random random = new Random();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        useFuture();

        TimeUnit.SECONDS.sleep(10);
        System.out.println();

        useCompletableFuture();
    }

    private static void useFuture() throws InterruptedException, ExecutionException {
        System.out.println("Future");
        ExecutorService exector = Executors.newFixedThreadPool(3);
        Future<Void> futureA = exector.submit(() -> work("A1"));
        Future<Void> futureB = exector.submit(() -> work("B1"));
        while (true) {
            try {
                futureA.get(1, TimeUnit.SECONDS);
                break;
            } catch (TimeoutException e) {
            }
            try {
                futureB.get(1, TimeUnit.SECONDS);
                break;
            } catch (TimeoutException e) {
            }
        }
        exector.submit(() -> work("C1"));
//        exector.shutdown 当线程池调用该方法时,线程池的状态则立刻变成SHUTDOWN状态。
//        此时，则不能再往线程池中添加任何任务，否则将会抛出RejectedExecutionException异常。
//        但是，此时线程池不会立刻退出，直到添加到线程池中的任务都已经处理完成，才会退出
//        exector.shutdown();

//        exector.shutdownNow执行该方法，线程池的状态立刻变成STOP状态，并试图停止所有正在执行的线程，不再处理还在池队列中等待的任务，
//        当然，它会返回那些未执行的任务。它试图终止线程的方法是通过调用Thread.interrupt()方法来实现的，
//        但是大家知道，这种方法的作用有限，如果线程中没有sleep 、wait、Condition、定时锁等应用, interrupt()方法是无法中断当前的线程的。
//        所以，ShutdownNow()并不代表线程池就一定立即就能退出，它可能必须要等待所有正在执行的任务都执行完成了才能退出。
        exector.shutdownNow();
    }

    private static void useCompletableFuture() throws InterruptedException, ExecutionException {
        System.out.println("CompletableFuture");
        CompletableFuture<Void> futureA = CompletableFuture.runAsync(() -> work("A2"));
        CompletableFuture<Void> futureB = CompletableFuture.runAsync(() -> work("B2"));
        futureA.runAfterEither(futureB, () -> work("C2")).get();
    }

    public static Void work(String name) {
        System.out.println(name + " starts at " + LocalTime.now());
        try {
            TimeUnit.SECONDS.sleep(random.nextInt(10));
        } catch (InterruptedException e) {
            System.out.println(name+":was interrupted");
        }
        System.out.println(name + " ends at " + LocalTime.now());
        return null;
    }
}
