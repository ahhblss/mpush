package com.github.mpusher.learn.base;

/**
 * Created by Sean.liu on 2017/6/29.
 */
public class ClassGetNameDemo {

    public static void main(String[] args) {
        System.out.println(new ClassGetNameDemo().getClass().getName());//fullname
        System.out.println(new ClassGetNameDemo().getClass().getSimpleName());//classname
        System.out.println(Integer.valueOf("11").getClass().getName());
    }
}
