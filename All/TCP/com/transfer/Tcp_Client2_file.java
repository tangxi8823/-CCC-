package com.transfer;

import java.io.*;
import java.net.Socket;

    public class Tcp_Client2_file {
        public static void main(String[] args) throws IOException {
            //创建客户端Scoket对象
            Socket s= new Socket("fe80::1c7d:9757:5d59:4da8%16",8090);

            //封装文本文件数据
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\79361\\Desktop\\images\\grapes.png"));

            //输出流
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            String line;
            while ((line = br.readLine())!=null){
                bw.write(line);
                bw.newLine();
                bw.flush();
            }

            //使输出终止
            s.shutdownOutput();

            //客户端接收反馈
            BufferedReader brClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String data = brClient.readLine();
            System.out.println("服务器的反馈："+data);

            //释放资源
            s.close();

            br.close();
        }
    }

