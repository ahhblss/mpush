package com.github.mpusher.learn.guava.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import java.util.concurrent.Executors;

/**
 * Created by Sean.liu on 2017/6/29.
 */
public class CustomEvent {

    @Subscribe
    public void sub(String message) {
        System.out.println(message);
    }

    public static void synEventBus() {
        EventBus eventBus = new EventBus();
        eventBus.register(new CustomEvent());

        eventBus.post("同步事件");
        System.out.println("==");
    }

    public static void asynEventBus() {
        AsyncEventBus eventBus = new AsyncEventBus(Executors.newFixedThreadPool(3));

        eventBus.register(new CustomEvent());

        eventBus.post("异步事件");

        System.out.println("==");
    }

    public static void deadEvent() {
//        Event对象的@Subscribe方法是public void sub(String message) ，
//        参数类型和post传递的参数不匹配，这将会造成Event的@Subscribe方法不被消费，
//        这个时候，EventBus会将此Event封装成DeadEvent
        EventBus eventBus = new EventBus();
        eventBus.register(new CustomEvent());

        eventBus.post(111);
        System.out.println("==");
    }

    public static void main(String[] args) {
//        synEventBus(); //"同步事件" print first
//        asynEventBus(); //"==" print first
        deadEvent();
    }
}
