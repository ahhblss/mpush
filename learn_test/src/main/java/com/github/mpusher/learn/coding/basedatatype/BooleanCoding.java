package com.github.mpusher.learn.coding.basedatatype;

/**
 * Created by Sean.liu on 2017/7/6.
 */
public class BooleanCoding {

    public static void main(String[] args) {
        int BIT5 = 1<<5;//      00000000 00000000 00000000 00100000(00000001<<5)
//        16进制就是逢16进1，0~9这十个数字相同，我们用A，B，C，D，E，F这六个字母来分别表示10，11，12，13，14，15。字母不区分大小写。
//        十六进制数的第0位的权值为16的0次方，第1位的权值为16的1次方，第2位的权值为16的2次方，依次类推。
        int BIT7 = 0x80;//      00000000 00000000 00000000 10000000 <=> 0*0+8*16=128
        int bitMap = 123456;//  00000000 00000001 11100010 01000000 <=> 123456

        System.out.println(BIT5);
        System.out.println(BIT7);

        System.out.println(bitMap|=BIT5);
        System.out.println(bitMap&=~BIT7);

    }

}
