package com.github.mpusher.learn.future;

import java.time.LocalTime;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Sean.liu on 2017/6/30.
 */
public class BasicFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Future<Integer> f = executorService.submit(()->{
            System.out.println("running:"+ LocalTime.now());
            return 2;
        });

        System.out.println(f.get());
    }
}
