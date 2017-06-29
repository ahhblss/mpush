package com.github.mpusher.learn.base;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Sean.liu on 2017/6/29.
 */
public class AtomicBooleanDemo {
    public static void main(String[] args) {
        AtomicBoolean started = new AtomicBoolean();
        started.set(true);
        System.out.println(started.get());
    }
}
