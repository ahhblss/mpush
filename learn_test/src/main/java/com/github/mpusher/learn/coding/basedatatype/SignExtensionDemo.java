package com.github.mpusher.learn.coding.basedatatype;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/7/5.
 * link:http://blog.csdn.net/pony_maggie/article/details/37535577
 */
public class SignExtensionDemo {
    public static void main(String[] args) {
        byte b = (byte)0x83;
        //计算机里从低精度数向高精度数转换时，比如这里从char到short, 肯定会在前面扩展一些bit位，从而达到高精度数的长度。
        // 那么扩展时，是补0还是补1呢？这里有个原则就是，有符号数扩展符号位，也就是1,无符号数扩展0
        short s1 = (short)b;
        short s2 = (short)(b&0xff);


        System.out.printf("s1 = %d\n", s1);
        System.out.printf("s2 = %d\n", s2);

        //Java中一个中文char的字节数是？答案为不确定（2，3，4），跟编码有关
        String chnStr = "华";
        try {
            System.out.println("length of one Chinese character in gbk: " + chnStr.getBytes("GBK").length );
            System.out.println("length of one Chinese character in UTF-8: " + chnStr.getBytes("UTF-8").length );
            System.out.println("length of one Chinese character in Unicode: " + chnStr.getBytes("UNICODE").length );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        char b1 = 0x83;
        short s11 = (short)b1;
        short s21 = (short)(b1&0xffff);
        System.out.println(b1);
        System.out.printf("s1 = %d\n", s11);
        System.out.printf("s2 = %d\n", s21);


    }
}
