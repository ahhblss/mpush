package com.github.mpusher.learn.coding.basedatatype;

import java.io.*;

/**
 * Created by Sean.liu on 2017/7/5.
 */
public class BruteForceCoding {
    private static byte byteVal = 101;    //       01100101
    private static short shortVal = 10001;//00100111 00010001
    private static int intVal = 100000001;//00000101 11110101 11100001 00000001
    private static long longVal = 1000000000001L;

    private final static int oneByte = Byte.SIZE;
    private final static int bSize = Byte.SIZE / oneByte;//1
    private final static int sSize = Short.SIZE / oneByte;//2
    private final static int iSize = Integer.SIZE / oneByte;//4
    private final static int lSize = Long.SIZE / oneByte;//8

    private final static int byteMask = 0xff;//11111111

    public static String byteArrayToDecimalString(byte[] data) {
        StringBuilder builder = new StringBuilder();
        for (byte b : data) {
            builder.append(b & byteMask).append(" ");
        }
        return builder.toString();
    }


    public static void encodeByDataOutputStream(){

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        DataOutputStream dp = new DataOutputStream(os);

        try {
            dp.writeByte(byteVal);
            dp.writeShort(shortVal);
            dp.writeInt(intVal);
            dp.writeLong(longVal);
            dp.flush();

            byte[] buf = os.toByteArray();
            System.out.println(byteArrayToDecimalString(buf));

            ByteArrayInputStream is = new ByteArrayInputStream(buf);
            DataInputStream di = new DataInputStream(is);
            System.out.println(di.readByte());//101
            System.out.println(di.readShort());//10001
            System.out.println(di.readInt());//100000001

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static int encodeIntBigEndian(byte[] dis, long val, int offSet, int length) {
        //long : 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000
        //int  : 00000000 00000000 00000000 00000000
        //short: 00000000 00000000
        //byte:  00000000
        for (int i = 0; i < length; i++) {
            dis[offSet++] = (byte) (val >> (length - i - 1) * Byte.SIZE);//右移
        }
        return offSet;
    }

    public static long decodeIntBigEndian(byte[] val, int offSet, int length) {
        //101 39(100111) 17(10001) 5(101) 245(11110101) 225(11100001) 1 0 0 0 232 212 165 16 1
        //byte -----short---------  -----------------int---------------- -----------long-------
        long rtn = 0;
        for (int i = 0; i < length; i++) {
            rtn = (rtn << oneByte) | ((long) val[offSet + i] & byteMask);//byteMask:11111111
        }
        return rtn;
    }

    public static void moreByteEncodeTest() {
        byte[] message = new byte[bSize + sSize + iSize + lSize];
        int offSet = encodeIntBigEndian(message, byteVal, 0, bSize);
        offSet = encodeIntBigEndian(message, shortVal, offSet, sSize);
        offSet = encodeIntBigEndian(message, intVal, offSet, iSize);
        offSet = encodeIntBigEndian(message, longVal, offSet, lSize);
        //101 39(100111) 17(10001) 5(101) 245(11110101) 225(11100001) 1 0 0 0 232 212 165 16 1
        System.out.println("encoded message:" + byteArrayToDecimalString(message));

        //decode
        long val = decodeIntBigEndian(message,bSize,sSize);
        System.out.println("shortval:"+val);
        val = decodeIntBigEndian(message,bSize+sSize+iSize,lSize);
        System.out.println("longval:"+val);
    }

    public static void main(String[] args) {
        //short to byte 高精度向低精度转变，高位会丢失
        short sv = 300;//00000001 00101100
        byte bv = (byte) sv;
        System.out.println(bv);

        //byte to short
        //计算机里从低精度数向高精度数转换时，比如这里从char到short, 肯定会在前面扩展一些bit位，从而达到高精度数的长度。
        // 那么扩展时，是补0还是补1呢？这里有个原则就是，有符号数扩展符号位，也就是1,无符号数扩展0
        byte bvv = 100;//01100100
        short svv = (short) bvv;
        System.out.println(svv);

//        moreByteEncodeTest();
        encodeByDataOutputStream();//101 39 17 5 245 225 1 0 0 0 232 212 165 16 1


    }
}
