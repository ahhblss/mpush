package com.github.mpusher.learn.coding.basedatatype;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Sean.liu on 2017/7/6.
 * link:http://www.cnblogs.com/dyllove98/archive/2013/07/26/3217741.html,http://blog.csdn.net/v_july_v/article/details/6685962
 */
public class BitMapDemo {

    private static int SHIFT = 5;
    private static int MASK = 0x1f; //00011111=>31
    private static int intSize = 32;//00100000

    /**
     * 初始化arry[index]所有bit位为0
     *
     * @param num
     * @param arry
     */
    public static void clearArray(int num, int[] arry) {
        int index = 0;
        index = num >> SHIFT;
        arry[index] &= 0;
    }

    /**
     * 设置所在的bit位为1
     *
     * @param n
     * @param arry
     */
    public static void setBitFlag(int n, int[] arry) {
        int index = 0, offset = 0;
        index = n >> SHIFT;// 等价于n / 32
        offset = n & MASK; // 等价于n % 32
        arry[index] |= 1 << offset;
        // n = 32 =>00100000 & 00011111 => 00000000=>1
        // n = 33 =>00100001 & 00011111 => 00000001=>1
        // n = 36 =>00100100 & 00011111 => 00000100=>4
        // n = 64 =>01000000 & 00011111 => 00000000=>0
        //n & MASK =>只保留0-31之间的数字，相当于取模
    }

    public static int testBitFlag(int n,int[] arry) {
        int i,flag;
        i =  1 << (n & MASK);//所在位bit为1
        flag = arry[n>>SHIFT] & i;//所在index的32位值分布
        return flag;
    }

    public static void bitMapSortDemo() {
    }

    public static void main(String[] args) {
        int max = 108 ;
        int[] arry = new int[(max>>5)+1];
        for (int temp : arry){
            clearArray(temp,arry);
        }
        for (int temp : arry){
            System.out.print(temp);
            System.out.print("\0");
        }
        System.out.println("------------");

        int testNum = 50;
        setBitFlag(testNum,arry);

        for (int temp : arry){
            ByteArrayOutputStream boutput = new ByteArrayOutputStream();
            DataOutputStream doutput = new DataOutputStream(boutput);
            try {
                doutput.writeInt(temp);
                byte[] buf = boutput.toByteArray();
                for (byte mm : buf){
                    System.out.print(mm);
                    System.out.print("\0");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println();
        }
        System.out.println();

        System.out.println(testBitFlag(testNum,arry));
    }

}
