package com.trans;

import com.safety.Aes;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//用于客户端向服务器下载文件中的阶段1.询问
public class serverAssertFile implements Runnable{
    private Socket socket;
    private int port;

    public serverAssertFile(Socket socket,int port) {
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
            //真实文件名
            String file = fileName.substring(3);
            System.out.println("1.the fileName client found is "+file);

            //[1]abc.txt 1的话是查询，2是下载
            if (fileName.charAt(1) == '1') {
                System.out.println("2.prepare asserting");
                //加入chord，获得文件所在port(IP地址)
                int portReal=TCPproperties.cloop.findServerByFile(file);
                //给出反馈->文件所在服务器地址
                BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                bwServer.write(Integer.toString(portReal));
                bwServer.newLine();
                bwServer.flush();
                //终止输出，这样输出流才能给客户端？？？
                socket.shutdownOutput();
                socket.close();
                System.out.println("already sent port");
                //thread.interrupt();
            }/*else if(fileName.charAt(1) == '2'){
                System.out.println("3.prepare sending");
                //new Thread(new serverSendFile(socket,file));
                int k=34131231;//k是密匙
                Aes aes = new Aes(k);//输入密匙创建Aes算法
                byte[] cipher =  aes.cipher("hello".getBytes());
                //封装文本文件数据
                try {
                    BufferedReader br2 = new BufferedReader(new FileReader("C:/Users/zxcvb/Desktop/images/moon.PNG"));
                    BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    String line2;
                    while((line2 = br2.readLine())!=null){
                        //bw.write(new String(aes.cipher(line2.getBytes())));
                        bw.write(line2);
                        bw.newLine();
                        bw.flush();
                    }
                    //使输出终止
                    socket.shutdownOutput();

                    //客户端接收反馈
                    BufferedReader brClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String data = brClient.readLine();
                    System.out.println("feedback from client"+data);

                    //释放资源
                    socket.close();
                    br.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }*/

        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("break from if else");
    }
}
