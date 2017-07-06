package com.github.mpusher.learn.coding.basedatatype;

import java.nio.charset.Charset;

/**
 * Created by Sean.liu on 2017/7/6.
 * 字符串不同的编码占用的字节数不一样
 */
public class StringCoding {
    public static void main(String[] args) {
        byte[] utf8 = "Test!".getBytes(Charset.forName("UTF-8"));
        for (byte demo:utf8 ){
            System.out.print(demo);
            System.out.print("\0");
        }//5 Bytes
        System.out.println();
        byte[] uft16Be = "Test!".getBytes(Charset.forName("UTF-16BE"));
        for (byte demo:uft16Be ){
            System.out.print(demo);
            System.out.print("\0");
        }//10 Bytes
    }
}
