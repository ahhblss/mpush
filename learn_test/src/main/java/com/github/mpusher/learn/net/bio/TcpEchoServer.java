package com.github.mpusher.learn.net.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Sean.liu on 2017/7/4.
 */
public class TcpEchoServer {

    private ServerSocket serverSocket;

    public TcpEchoServer() throws IOException {
        serverSocket = new ServerSocket(Config.port);
    }

    public void service() {
        Socket socket = null;
        while (true) {

            try {
                socket = serverSocket.accept();
                new Thread(new EchoTask(socket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) throws IOException {
        new TcpEchoServer().service();
    }
}
