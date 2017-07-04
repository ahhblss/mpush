package com.github.mpusher.learn.net.udp;

import java.io.IOException;
import java.net.*;

/**
 * Created by Administrator on 2017/7/4.
 */
public class Receive {
    public static void main(String[] args) {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            int port = 8033;

            DatagramSocket receiveSocket = new DatagramSocket(port, ip);
            byte[] getBuf = new byte[1024];
            DatagramPacket getPacket = new DatagramPacket(getBuf, getBuf.length);
            receiveSocket.receive(getPacket);

            String getMes = new String(getBuf, 0, getPacket.getLength());
            System.out.println("对方发送的消息：" + getMes);

            InetAddress sendIp = getPacket.getAddress();
            int sendPort = getPacket.getPort();
            System.out.println("对方的IP地址是：" + sendIp.getHostAddress());
            System.out.println("对方的端口号是：" + sendPort);

            SocketAddress sendSocketAddress = getPacket.getSocketAddress();
            String response = "接收方说：我收到了";
            byte[] sendBuf = response.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length, sendSocketAddress);
            receiveSocket.send(sendPacket);

            receiveSocket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
