package com.github.mpusher.learn.net.backlog;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Sean.liu on 2017/7/4.
 */
public class Server {
    private int port = 8022;
    private ServerSocket serverSocket;

    public Server() throws IOException {
        serverSocket = new ServerSocket(port,3);
        System.out.println("server started");
    }

    public void service(){
        Socket socket = null;
        while (true){
            try {
                socket = serverSocket.accept();
                System.out.println("new connection connected:"+socket.getInetAddress()+"-"+socket.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server();
//        Thread.sleep(60000*10);
        server.service();
    }
}
