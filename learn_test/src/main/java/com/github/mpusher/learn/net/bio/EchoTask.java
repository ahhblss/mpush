package com.github.mpusher.learn.net.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Sean.liu on 2017/7/4.
 */
public class EchoTask implements Runnable {

    private Socket socket;

    public EchoTask(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            System.out.println(socket.getRemoteSocketAddress());
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            byte[] data;
            int i = 1;
            while (true) {
                data = new byte[1024];
                int length = is.read(data);
                String msg = new String(data,0,length);
                if ("exit".equals(msg)) {
                    break;
                } else {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println("server receive:" + msg);
                }
                ++i;
                if (i == 5) {
                    os.write("exit".getBytes());
//                    os.flush();
                } else {
                    os.write(("server" + i).getBytes());
//                    os.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
