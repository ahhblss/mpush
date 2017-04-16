package com.github.mpusher.learn.jdk8_features;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */
public class LambdaL {
    public static void main(String[] args) {
//        Arrays.asList("a", "b", "c").forEach(e -> System.out.println(e));

        List<String> list = Arrays.asList("a", "d", "b");
        list.sort((e1, e2) -> e1.compareTo(e2));
        list.forEach(e-> System.out.println(e));

        Arrays.asList("a", "b", "d").sort((e1, e2) -> {
            int result = e1.compareTo(e2);
            return result;
        });

    }
}
