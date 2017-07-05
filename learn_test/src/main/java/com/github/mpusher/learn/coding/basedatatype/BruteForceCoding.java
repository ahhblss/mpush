package com.github.mpusher.learn.coding.basedatatype;

/**
 * Created by Sean.liu on 2017/7/5.
 */
public class BruteForceCoding {
    private static byte byteVal = 101;    //       01100101
    private static short shortVal = 10001;//100111 00010001
    private static int intVal = 100000001;
    private static long longVal = 1000000000001L;

    private final static int oneByte = Byte.SIZE;
    private final static int bSize = Byte.SIZE/oneByte;//1
    private final static int sSize = Short.SIZE/oneByte;//2
    private final static int iSize = Integer.SIZE/oneByte;//4
    private final static int lSize = Long.SIZE/oneByte;//8

    private final static int byteMask = 0xff;

    public static String byteArrayToDecimalString(byte[] data){
        StringBuilder builder = new StringBuilder();
        for (byte b:data){
            builder.append(b & byteMask).append(" ");
        }
        return builder.toString();
    }


    public static int encodeIntBigEndian(byte[] dis,long val,int offSet,int length){
        //long : 00000000 00000000 00000000 00000000 00000000 00000000 00000000 00000000
        //int  : 00000000 00000000 00000000 00000000
        //short: 00000000 00000000
        //byte:  00000000
        for (int i=0;i<length;i++){
            dis[offSet++] = (byte)(val>>(length-i-1)*Byte.SIZE);
        }
        return offSet;
    }

    public static void moreByteEncodeTest(){
        byte[] message = new byte[bSize+sSize+iSize+lSize];
        int offSet = encodeIntBigEndian(message,byteVal,0,bSize);
        System.out.println(offSet);
        offSet = encodeIntBigEndian(message,shortVal,offSet,sSize);
        System.out.println(offSet);
        offSet = encodeIntBigEndian(message,intVal,offSet,iSize);
        System.out.println(offSet);
        offSet = encodeIntBigEndian(message,longVal,offSet,lSize);
        System.out.println(offSet);
        System.out.println("encoded message:"+byteArrayToDecimalString(message));
    }

    public static void main(String[] args) {
        //short to byte 高精度向低精度转变，高位会丢失
        short sv = 300;//00000001 00101100
        byte bv = (byte)sv;
        System.out.println(bv);

        //byte to short
        byte bvv = 100;//01100100
        short svv = (short)bvv;
        System.out.println(svv);

    }
}
