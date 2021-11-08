package com.transfer;

import com.safety.Aes;
import com.safety.RSA;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Arrays;

public class Tcp_Client_file {
    public static void main(String[] args) throws IOException {
        //创建客户端Scoket对象
        Socket s= new Socket("fe80::1c7d:9757:5d59:4da8%16",8088);

        //--------------AES实例化-----------------
        int k=34131231;//k是密匙
        Aes aes = new Aes(k);//输入密匙创建Aes算法
        //--------------RSA实例化 生成公钥密钥----------------
        BigInteger p = new BigInteger("997");
        BigInteger q = new BigInteger("1009");

        RSA rsa = new RSA();
        // 生成公钥私钥'''
        // pubkey, selfkey = gen_key(p, q)
        BigInteger[][] keys = rsa.genKey(p, q);
        BigInteger[] pubkey = keys[0];
        BigInteger[] selfkey = keys[1];
        String plainText = keys[0].toString();//aes明文   (RSA生成的公钥)keys[0]
        String plainTxxt = "amg";
        byte[] cipherText = aes.cipher(plainTxxt.getBytes());//密文字节

        //--------------AES传递k和RSA公钥-----------------
        //封装k和RSA公钥,发送给服务器端
        InputStream is = new ByteArrayInputStream(cipherText);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));/*
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        byte [] b = new byte[4];
        while (-1 != is.read(b)) {
            System.out.println("    发送的byte[]：" + Arrays.toString(b));
            dos.write(b); // 发送给客户端
        }
        dos.flush();
        dos.close();*/

        //封装文本文件数据，发送给服务器端
        //BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\79361\\Desktop\\images\\grapes.png"));

        //输出流
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        String line_2;
        while ((line_2 = br.readLine())!=null){
            bw.write(line_2);
            bw.newLine();
            bw.flush();
        }

        //-----输出已经用AES加密完的RSA公钥--------
        //InputStream is = new ByteArrayInputStream(cipherText);
        //br = new BufferedReader(new InputStreamReader(is));

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
