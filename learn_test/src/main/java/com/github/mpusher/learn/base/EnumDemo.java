package com.github.mpusher.learn.base;

/**
 * Created by Administrator on 2017/6/29.
 */
public enum EnumDemo {
    MOBILE(1, "android", "ios"),
    PC(2, "windows", "mac", "linux"),
    WEB(3, "web", "h5"),
    UNKNOWN(-1);

    public final int type;
    public final String[] os;

    EnumDemo(int type, String... os) {
        this.type = type;
        this.os = os;
    }

    public static void main(String[] args) {
        for (EnumDemo type : values()) {
            System.out.println(type.type);
        }
    }
}
