package com.trans;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.chord.ChordLoop;


public class Server2 {
    private final int port;

    private Integer[] listServer = {8088,8089,8090,8091};

    public Server2(int port) {
        this.port = port;
    }

    public Integer[] getListServer() {
        return listServer;
    }

    public void setListServer(Integer[] listServer) {
        this.listServer = listServer;
    }

    public int getPort() {
        return port;
    }

//    public class MyThread extends Thread {
//        public Socket socket;
//
//        public MyThread(Socket socket){
//            this.socket = socket;
//        }
//
//        @Override
//        public  void run() {
//            System.out.println("Thread is running");
//            new Thread(new serverAssertFile(socket,port)).start();
//        }
//    }

    public void serverRun() throws IOException {
        //创建服务器套接字
        ServerSocket ss = new ServerSocket(this.port);
        //为每一个客户端开启一个线程
        while (true) {
            //监听客户端连接
            System.out.println("listen11111");
            Socket s = ss.accept();
            System.out.println("listen222222");

            //确认文件是否在本服务器端
            Thread t=new Thread(new serverAssertFile(s,port));
            t.start();
            t.interrupt();
            System.out.println("over");
            //new Thread(new serverAssertFile(s,port)).start();
        }
    }
    public static void main(String[] args) throws IOException {
        //初始化哈希环，
        TCPproperties.cloop=new ChordLoop();
        Server server1 = new Server(60088);
        Server server2 = new Server(60090);
        Server server3 = new Server(60092);
        //将服务器映射到哈希环里
        TCPproperties.cloop.addNode(server1);
        TCPproperties.cloop.addNode(server2);
        TCPproperties.cloop.addNode(server3);
        //至此，已经将环建好。三个服务器进入监听状态
        server2.serverRun();
//        server2.serverRun();
//        server3.serverRun();

    }

}

