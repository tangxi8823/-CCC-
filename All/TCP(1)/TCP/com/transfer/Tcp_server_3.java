package com.transfer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import cs.ServerUploadThread;

public class Tcp_server_3 {
    public static void main(String[] args) throws IOException {
        //创建客户端Socket对象
        ServerSocket ss = new ServerSocket(8091);

        //为每一个客户端开启一个线程
        while (true) {
            //监听客户端连接
            Socket s = ss.accept();

            new Thread(new ServerUploadThread(s)).start();
        }


    }

}
