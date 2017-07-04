package com.github.mpusher.learn.net.backlog;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Sean.liu on 2017/7/4.
 */
public class Client {

    private static String host = "localhost";
    private static int port = 8022;

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket[] sockets = new Socket[100];
        for (int i = 0; i < 100; i++) {
            sockets[i] = new Socket(host,port);
            System.out.println(String.format("第%d连接成功",i));
        }

        Thread.sleep(3000);
        for (int i = 0; i < 100; i++) {
            sockets[i].close();
        }
    }
}
