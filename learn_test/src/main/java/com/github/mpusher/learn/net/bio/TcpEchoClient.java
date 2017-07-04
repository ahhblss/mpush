package com.github.mpusher.learn.net.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Sean.liu on 2017/7/4.
 */
public class TcpEchoClient {

    public void run() {
        Socket client = new Socket();

        try {
            client.setSendBufferSize(1*1024);
            client.connect(new InetSocketAddress(Config.host, Config.port));
            if (client.isConnected()) {
                System.out.println("connected server");
                InputStream is = client.getInputStream();
                OutputStream os = client.getOutputStream();

                os.write(Config.msg.getBytes());
                System.out.println("send msg:from client;waiting for response");
//                os.flush();

                byte[] data;


                int i = 1;
                while (true) {
                    data = new byte[1024];
                    /* read:This method blocks until input data is
                       available, end of file is detected,
                       or an exception is thrown
                    */
                    int length = is.read(data);
                    String response = new String(data,0,length);
                    if ("exit".equals(response)) {
                        System.out.println("exit");
                        break;
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }

                    ++i;
                    if (i == 5) {
                        os.write("exit".getBytes());
//                        os.flush();
                    } else {
                        os.write(("client" + i).getBytes());
//                        os.flush();
                        System.out.println("send data :client" + i);
                    }

                }


                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TcpEchoClient().run();
    }
}
