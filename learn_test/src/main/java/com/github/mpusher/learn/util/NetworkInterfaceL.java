package com.github.mpusher.learn.util;

import com.mpush.tools.Utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Created by Sean.liu on 2017/4/14.
 */
public class NetworkInterfaceL {
    public static void main(String[] args) {
        System.out.println(Utils.getLocalIp());
    }
}
