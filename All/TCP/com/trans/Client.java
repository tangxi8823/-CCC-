package com.trans;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

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
        //System.out.println("panda");
        //while(connection_state){
            System.out.print("5.Input type:");
            String fileName = "grapes.png";
            Scanner in = new Scanner(System.in);
            String type = in.nextLine();
            if(type.equals("download"))
            {
                connect();
                System.out.println("Downloading");
                //下载
                new Thread(new clientDownloadFile(socket,"[2]"+fileName)).start();
            }else if(type .equals("upload")){
                //上传
                connect();
            }

    }
    //创建与服务器端的连接
    public static void connect(){
        System.out.println("connect() success!");
        try{
            socket = new Socket(TCPproperties.ip,port);
            connection_state = true;
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    //socket会断连，所以会增加reconnect函数
    public static void reconnect(){
        while (!connection_state){
            System.out.println("try reconnect().....");
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
        Client c1 = new Client(60088);
        c1.clientRun();
        System.out.println("4.connect with the real server:"+getPort());
        Client c2 = new Client(port);
        c2.clientRun2();
    }
}
