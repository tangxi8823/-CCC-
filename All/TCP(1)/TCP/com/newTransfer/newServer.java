package com.newTransfer;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class newServer {
    public static void main(String[] args){
        System.out.println("Socket开始运行");
        //AES算法实例化时需要的k
        int k=34131231;
        try {
        	//这个服务器的端口
            ServerSocket server = new ServerSocket(8088);
            while(true){
                // 阻塞等待，每接收到一个请求就创建一个新的连接实例
                Socket socket = server.accept();
                new Thread(new server_listen_pubkey(socket,k)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class server_listen_pubkey implements Runnable{
    private Socket socket;
    private int aes_k;
    public server_listen_pubkey(Socket socket,int aes_k) {
        this.socket = socket;
        this.aes_k = aes_k;
    }
    @Override
    public void run() {
        // 装饰流BufferedInputStream封装输入流（接收客户端的流）
        try {
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            DataInputStream dis = new DataInputStream(bis);
            byte[] bytes = new byte[4]; // 一次读取一个byte
            String ret = "";//接收到的公钥
            while (dis.read(bytes) != -1) {
                System.out.println("    收到的byte[]：" + Arrays.toString(bytes));
                ret += new String(bytes, StandardCharsets.UTF_8);
                if (dis.available() == 0) { //一个请求完成  输出
                    System.out.println("收到的完成内容：" + ret);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class server_listen_info implements Runnable{
    private Socket socket;
    private byte[] pubkey;

    public server_listen_info(Socket socket,byte[] pubkey) {
        this.socket = socket;
        this.pubkey = pubkey;
    }

    @Override
    public void run() {

    }

}