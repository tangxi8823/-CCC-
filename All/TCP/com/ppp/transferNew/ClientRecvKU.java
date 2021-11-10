package com.ppp.transferNew;

import com.safety.Aes;
import com.safety.RSA;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientRecvKU implements Runnable{
    private Socket socket;
    private int port;

    public ClientRecvKU(Socket socket,int port){
        this.socket = socket;
        this.port = port;
    }

    @Override
    public void run() {
        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Reader转String
            StringBuffer buffer_KU = new StringBuffer();
            String line = " ";
            while ((line = br.readLine()) != null){
                buffer_KU.append(line);
            }
            String KU = buffer_KU.toString();
            //将RSA公钥输出
            System.out.println("KU:"+KU);

            //给出反馈
            BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bwServer.write("#告诉服务器端已经获得KU");
            bwServer.newLine();
            bwServer.flush();

            //生成AES的k
            int k=34131231;//k是密匙
            Aes aes = new Aes(k);//输入密匙创建Aes算法

            //用RSA公钥加密AES的key
            RSA rsa = new RSA();
            BigInteger b1 = new BigInteger("1005973");
            BigInteger b2 = new BigInteger("3889");
            BigInteger[] pubkey = {b1,b2};
            BigInteger c = rsa.encrypt(new BigInteger(String.valueOf(k)), pubkey);

            //socket会不会断了？？？
            //--------传输AES的k--------
            try {
                InputStream is = new ByteArrayInputStream(String.valueOf(k).getBytes(StandardCharsets.UTF_8));
                BufferedReader br2 = new BufferedReader(new InputStreamReader(is));

                //输出流
                BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                String line2;
                while ((line2 = br2.readLine())!=null){
                    bw2.write(line2);
                    bw2.newLine();
                    bw2.flush();
                }

                //使输出终止
                socket.shutdownOutput();
                System.out.println("#AES的k已经传输给客户端");


                //客户端接收AES加密过后的密文
                BufferedReader brClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String fileContent = brClient.readLine();
                System.out.println("#服务器传输的文件信息："+fileContent);

                //释放资源
                //socket 等一下释放
                socket.close();
                Client.connection_state = false;
                Client.reconnect();
                br.close();

            }catch (IOException e){
                e.printStackTrace();
            }



        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
