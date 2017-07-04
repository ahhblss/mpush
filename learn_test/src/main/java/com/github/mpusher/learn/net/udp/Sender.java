package com.github.mpusher.learn.net.udp;

import java.io.IOException;
import java.net.*;

/**
 * Created by Administrator on 2017/7/4.
 */
public class Sender {
    public static void main(String[] args) {
        try {
            DatagramSocket sendSocket = new DatagramSocket();
            sendSocket.setSoTimeout(3000);

            String msg = "你好，接收方";
            byte[] sendBuf = msg.getBytes();

            int port = 8033;
            InetAddress ip = InetAddress.getLocalHost();

            DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, ip, port);
            sendSocket.send(sendPacket);

            byte[] getBuf = new byte[1024];
            DatagramPacket getPacket = new DatagramPacket(getBuf, getBuf.length);
            /*This method blocks until a datagram is received*/
            sendSocket.receive(getPacket);

            String backMsg = new String(getBuf, 0, getPacket.getLength());
            System.out.println(backMsg);

            sendSocket.close();

        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
