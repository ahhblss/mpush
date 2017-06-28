package com.github.mpusher.learn.jdk8_features.inteface;

/**
 * Created by Administrator on 2017/6/28.
 */
public interface InterfaceSample {

    public void show();

    /**
     * 一些不需要重写的方法我们我们就直接在接口中定义
     */
    static void noNeedImp(){
        System.out.println("no need to impl");
    }

    default void defaultMethod(){
        System.out.println("default method");
    }
}
