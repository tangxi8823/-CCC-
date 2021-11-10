package com.trans;

import com.safety.Aes;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class serverSendKU implements Runnable{
    private Socket socket;
    private BigInteger[] pubkey;

    public serverSendKU(Socket socket,BigInteger[] pubkey) {
        this.socket = socket;
        this.pubkey = pubkey;
    }

    @Override
    public void run() {
        //传输RSA公钥
        //-------1.先将BigInteger[]转成String模式
        int i = 0;
        String KU = "";
        for(;i< pubkey.length;i++) {
            KU += String.valueOf(pubkey[i]);
        }
        //-------2.io流封装String
        try {
            InputStream is = new ByteArrayInputStream(KU.getBytes(StandardCharsets.UTF_8));
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

            //服务器端接收反馈
            BufferedReader brClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String aes_k = brClient.readLine();
            System.out.println("客户端反馈aes_k：" + aes_k);
            //将k（string）转换成int
            int aesK = Integer.parseInt(aes_k);

            //加密文件信息
            Aes aes = new Aes(aesK);
            byte[] cipher = aes.cipher("file".getBytes(StandardCharsets.UTF_8));
            //传递cipher
            InputStream is2 = new ByteArrayInputStream(cipher);
            BufferedReader br2 = new BufferedReader(new InputStreamReader(is));

            //输出流
            BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String line2;
            while ((line2 = br2.readLine())!=null){
                bw2.write(line);
                bw2.newLine();
                bw2.flush();
            }

            //使输出终止
            socket.shutdownOutput();

            //释放资源
            //socket 等一下释放
            //socket.close();
            br.close();
            brClient.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
