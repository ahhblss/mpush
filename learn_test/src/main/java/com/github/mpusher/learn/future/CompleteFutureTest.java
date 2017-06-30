package com.github.mpusher.learn.future;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by Sean.liu on 2017/6/30.
 * link:http://colobu.com/2016/02/29/Java-CompletableFuture/#主动完成计算
 */
public class CompleteFutureTest {

    private static Random rand = new Random();
    private static long t = System.currentTimeMillis();
    static int getMoreData() {
        System.out.println("begin to start compute");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("end to start compute. passed " + (System.currentTimeMillis() - t)/1000 + " seconds");
        return rand.nextInt(1000);
    }


    public static void whenCompleteDemo() throws ExecutionException, InterruptedException, IOException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(CompleteFutureTest::getMoreData);
//        BiConsumer=>Represents an operation that accepts two input arguments and returns no
//        result
//        whenComplete方法不以Async结尾，意味着Action使用相同的线程执行，
//        而Async可能会使用其它的线程去执行(如果使用相同的线程池，也可能会被同一个线程选中执行)
        Future<Integer> f = future.whenComplete((v, e) -> {
            System.out.println(v);
            System.out.println(e);
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println(f.get());
        System.out.println(Thread.currentThread().getName());
    }

    /**
     * runnable接口 无返回值
     */
    public static void runAsyncDemo(){
        CompletableFuture<Void> future = CompletableFuture.runAsync(()->{
            System.out.println("runAsync");
        });
    }


    /**
     * Supplier<U> supplier;有返回值
     */
    public static void supplyAsyncDemo() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
            return "supplyAsync";
        });

        System.out.println(future.get());

    }


    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
//        runAsyncDemo();
//        supplyAsyncDemo();
        whenCompleteDemo();
    }

}
