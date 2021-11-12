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
        //�����������׽���
        ServerSocket ss = new ServerSocket(this.port);
        //Ϊÿһ���ͻ��˿���һ���߳�
        while (true) {
            //�����ͻ�������
            System.out.println("listen11111");
            Socket s = ss.accept();
            System.out.println("listen222222");

            //ȷ���ļ��Ƿ��ڱ���������
            Thread t=new Thread(new serverAssertFile(s,port));
            t.start();
            t.interrupt();
            System.out.println("over");
            //new Thread(new serverAssertFile(s,port)).start();
        }
    }
    public static void main(String[] args) throws IOException {
        //��ʼ����ϣ����
        TCPproperties.cloop=new ChordLoop();
        Server server1 = new Server(60088);
        Server server2 = new Server(60090);
        Server server3 = new Server(60092);
        //��������ӳ�䵽��ϣ����
        TCPproperties.cloop.addNode(server1);
        TCPproperties.cloop.addNode(server2);
        TCPproperties.cloop.addNode(server3);
        //���ˣ��Ѿ��������á������������������״̬
        server2.serverRun();
//        server2.serverRun();
//        server3.serverRun();

    }

}

