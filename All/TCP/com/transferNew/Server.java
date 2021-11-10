package com.transferNew;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    private final int port;

    private Integer[] listServer = {8088,8090,8092};

    public Server(int port) {
        this.port = port;
    }


    public void serverRun() throws IOException {
        //创建服务器套接字
        ServerSocket ss = new ServerSocket(this.port);
        //为每一个客户端开启一个线程
        while (true) {
            //监听客户端连接
            Socket s = ss.accept();
            //确认文件是否在本服务器端
            new Thread(new serverAssertFile(ss,s,port)).start();
        }
    }
    public static void main(String[] args) throws IOException {
        Server server1 = new Server(8088);
        server1.serverRun();
        Server server2 = new Server(8090);
        server2.serverRun();
    }

}

