package com.trans;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class clientDownloadFile implements Runnable {
    private Socket socket;
    //private int portReal;
    private String fileName;

    public clientDownloadFile(Socket socket, String fileName) {
        this.socket = socket;
        //this.portReal = portReal;
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
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            //终止输出，这样输出流才能给服务器端
            socket.shutdownOutput();

            //接受加密的文件信息
            //客户端接收反馈
            BufferedReader brClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String data = brClient.readLine();
            System.out.println("服务器的明文："+data);

            //释放资源
            socket.close();

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}