package com.trans;

import com.safety.Aes;
import com.safety.RSA;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;

public class serverSendFile implements Runnable{
    private Socket socket;
    private String fileName;

    public serverSendFile(Socket socket, String fileName) {
        this.socket = socket;
        this.fileName = fileName;
    }

    @Override
    public void run() {
    //AES的k
        int k=34131231;//k是密匙
        Aes aes = new Aes(k);//输入密匙创建Aes算法
//        byte[] cipher =  aes.cipher("".getBytes());
        //封装文本文件数据
        try {
            System.out.println("3.prepare sending");
            BufferedReader br = new BufferedReader(new FileReader("C:/Users/zxcvb/Desktop/images/moon.PNG"));
            BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line;
            int times =3;
            //while((line = br.readLine())!=null)
            while(times!=0){
               // bw.write(new String(aes.cipher(line.getBytes())));
                bw.write("hello");
                System.out.println(times);
                bw.newLine();
                bw.flush();
                times--;
            }
            socket.shutdownOutput();

            //客户端反馈
            BufferedReader br2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Reader转String
            StringBuffer info = new StringBuffer();
            String line2 = " ";
            while ((line2 = br.readLine()) != null){
                info.append(line2);
            }
            String clientInfo = info.toString();
            //真实文件名
            System.out.println("7.Final clientInfo"+clientInfo);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
