package com.ppp.transferNew;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class serverAssertFile implements Runnable{
    private ServerSocket ss;
    private Socket socket;
    private int port;

    public serverAssertFile(ServerSocket ss,Socket socket,int port) {
        this.ss = ss;
        this.socket = socket;
        this.port = port;
    }

    @Override
    public void run() {
        //将文件名解封
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Reader转String
            StringBuffer buffer_fileName = new StringBuffer();
            String line = " ";
            while ((line = br.readLine()) != null){
                buffer_fileName.append(line);
            }
            String fileName = buffer_fileName.toString();
            System.out.println("1.客户端想要找寻的文件:"+fileName);

            //给出反馈
            BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bwServer.write("文件名解析成功");
            bwServer.newLine();
            bwServer.flush();

            //查找文件是否在本地服务端或是其他服务端（chord算法），如果不在本地，则返回实际服务端端口号
/*            if(){
                //如果在本地，则直接开启下载线程
                Socket s = ss.accept();
                new Thread(new serverSendFile(s,fileName)).start();
            }else{
                //如果在其他服务器端，则返回real port

            }*/
            //上面模块写好后要删除！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
            Socket s = ss.accept();
            new Thread(new serverSendFile(s,fileName)).start();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
