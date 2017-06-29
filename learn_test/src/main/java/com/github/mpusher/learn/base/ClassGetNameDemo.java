package com.github.mpusher.learn.base;

/**
 * Created by Sean.liu on 2017/6/29.
 */
public class ClassGetNameDemo {

    public static void main(String[] args) {
        System.out.println(new ClassGetNameDemo().getClass().getName());
        System.out.println(Integer.valueOf("11").getClass().getName());
    }
}
