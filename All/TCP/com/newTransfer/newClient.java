package com.newTransfer;

import com.safety.Aes;
import com.safety.RSA;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Arrays;

public class newClient {


    public static void main(String[] args){
        //创建客户端Scoket对象
        try {
            Socket socket= new Socket("fe80::1c7d:9757:5d59:4da8%16",8088);
            int aes_k = 34131231;//k是AES密匙
            new Thread(new client_send_Pubkey(socket,aes_k));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

class client_send_Pubkey implements Runnable{
    private Socket socket;
    private int aes_k;
    public client_send_Pubkey(Socket socket,int aes_k) {
        this.socket = socket;
        this.aes_k = aes_k;
    }

    @Override
    public void run() {

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

        //-------------AES加密RSA公钥-------------------------
        Aes aes = new Aes(aes_k);//输入密匙创建Aes算法
        byte[] cipherText = aes.cipher(plainText.getBytes());//密文字节

        //------------封装RSA公钥,发送给服务器端（io流）-----------------
        try {
            InputStream is = new ByteArrayInputStream(cipherText);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            byte [] b = new byte[4];
            while (-1 != is.read(b)) {
                System.out.println("    发送的byte[]：" + Arrays.toString(b));
                dos.write(b); // 发送给客户端
            }
            dos.flush();
            dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}