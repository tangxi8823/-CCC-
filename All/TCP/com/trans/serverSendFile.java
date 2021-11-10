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
        byte[] cipher =  aes.cipher("".getBytes());
        //封装文本文件数据
        try {
            BufferedReader br = new BufferedReader(new FileReader("C:Users/79361/Desktop/images/grapes.png"));
            BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line;
            while((line = br.readLine())!=null){
                bw.write(new String(aes.cipher(line.getBytes())));
                bw.newLine();
                bw.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
