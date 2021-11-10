package com.trans;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private static int port;

    private static Socket socket;

    public static boolean connection_state = false;

    public Client(int port) {
        this.port = port;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Client.port = port;
    }

    public void clientRun(){
       // while(!connection_state){
            connect();
            String fileName = "grapes.png";
            //查询
            new Thread(new clientFindServer(socket,"[1]"+fileName)).start();
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        //}
    }

    public void clientRun2(){
        System.out.println("panda");
        //while(connection_state){
            System.out.println("monkey");
            connect();
            String fileName = "grapes.png";
            //下载
            System.out.println("下载！！！！！！");
            new Thread(new clientDownloadFile(socket,"[2]"+fileName)).start();
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        //}
    }
    //创建与服务器端的连接
    public static void connect(){
        try{
            socket = new Socket(TCPproperties.ip,port);
            connection_state = true;
            System.out.println("###连接成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //socket会断连，所以会增加reconnect函数
    public static void reconnect(){
        while (!connection_state){
            System.out.println("尝试重新链接.....");
            connect();
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
    	//随机更好 待
        Client c1 = new Client(50088);
        c1.clientRun();
        System.out.println("4.与真实端口建立连接:"+getPort());
        Client c2 = new Client(port);
        c2.clientRun2();
        System.out.println("5");
    }
}
