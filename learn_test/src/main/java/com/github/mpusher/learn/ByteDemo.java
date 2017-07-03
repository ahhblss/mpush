package com.github.mpusher.learn;

/**
 * Created by Sean.liu on 2017/7/3.
 * link: com.mpush.api.protocol.Packet
 */
public class ByteDemo {

    public static void main(String[] args) {
        byte FLAG_CRYPTO = 1;     //0000 0001
        byte FLAG_COMPRESS = 2;   //0000 0010
        byte FLAG_BIZ_ACK = 4;    //0000 0100
        byte FLAG_AUTO_ACK = 8;   //0000 1000
        byte FLAG_JSON_BODY = 16; //0001 0000
        System.out.println(FLAG_CRYPTO);
        System.out.println(FLAG_COMPRESS);

        byte flags = 1;           //0000 0001

        //addFlag
        System.out.println(flags |= FLAG_JSON_BODY);

        //hasFlag                 //0001 0001
        System.out.println((flags & FLAG_JSON_BODY) != 0);

//        &:两个操作数中位都为1，结果才为1，否则结果为0
//        |:两个位只要有一个为1，那么结果就是1，否则就为0
//        ~:如果位为0，结果是1，如果位为1，结果是0
//        ^:两个操作数的位中，相同则结果为0，不同则结果为1
    }
}
