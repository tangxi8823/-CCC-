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
        //接受数据写入到文件

            try {

                //Socket socket = new Socket(TCPproperties.ip,60090);

                //解决名称冲突问题
                int count = 1;
                File file = new File("C:/Users/zxcvb/Desktop/images/Copy["+count+"].txt");
                while (file.exists()){
                    count++;
                    file = new File("C:/Users/zxcvb/Desktop/images/Copy["+count+"].txt");
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                java.lang.String line;
                System.out.println("6.file info ");
                while ((line=br.readLine())!=null){
                    bw.write(line);
                    System.out.print(line+" ");
                    bw.newLine();
                    bw.flush();
                }
                //给出反馈
                BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bwServer.write("file is downloaded");
                bwServer.newLine();
                bwServer.flush();
                socket.shutdownOutput();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


