package com.github.mpusher.learn.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Sean.liu on 2017/7/4.
 */
public class TimeoutTester {
    public static void main(String[] args) throws IOException {
        ServerSocket  serverSocket = new ServerSocket(0);
        System.out.println(serverSocket.getReceiveBufferSize());
        serverSocket.setSoTimeout(3000);
        Socket socket = serverSocket.accept();
        socket.close();
        serverSocket.close();
        System.out.println("server closed");
    }
}
