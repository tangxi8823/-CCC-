package com.transferNew;

import com.safety.RSA;

import java.io.File;
import java.math.BigInteger;
import java.net.Socket;

public class serverSendFile implements Runnable{
    private Socket socket;
    private String fileName;

    public serverSendFile(Socket socket, String fileName) {
        this.socket = socket;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        //---------------生成RSA公私钥对，并传输私钥--------------------
        // 公钥私钥中用到的两个大质数p,q,不要修改'''
        BigInteger p = new BigInteger("997");
        BigInteger q = new BigInteger("1009");
        RSA rsa = new RSA();

        // 生成公钥私钥
        BigInteger[][] keys = rsa.genKey(p, q);
        BigInteger[] pubkey = keys[0];
        BigInteger[] selfkey = keys[1];

        //传输RSA公钥
        new Thread(new serverSendKU(socket,pubkey)).start();

    }
}
