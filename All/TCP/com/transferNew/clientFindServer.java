package com.transferNew;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class clientFindServer implements Runnable{
    private Socket socket;
    private String fileName;

    public clientFindServer(Socket socket,String fileName) {
        this.socket = socket;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        //io流封装文件名
        try {
            InputStream is = new ByteArrayInputStream(this.fileName.getBytes(StandardCharsets.UTF_8));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            //输出流
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line;
            while ((line = br.readLine())!=null){
                bw.write(line);
                bw.newLine();
                bw.flush();
            }

            //使输出终止
            socket.shutdownOutput();

            //客户端接收反馈
            BufferedReader brClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String data = brClient.readLine();
            System.out.println("2.服务器的反馈:"+data);//fileName解析成功

            //释放资源
            socket.close();
            Client.connection_state = false;
            Client.reconnect();
            br.close();
            System.out.println("3.clientFndServer线程结束，此时socket断开后，重连");

            //此处先写死
            System.out.println("4.准备接收服务器端发来的KU");
            new Thread(new ClientRecvKU(socket,8088));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
