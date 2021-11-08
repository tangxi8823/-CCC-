package com.safety;

public class AesTest {
    public static void main(String[] args){
        int k=34131231;//k是密匙
        Aes aes = new Aes(k);//输入密匙创建Aes算法
        //--------加密-------------------------
        String plainText = "12343qsd321";//明文
        byte[] cipherText = aes.cipher(plainText.getBytes());//密文字节
        //--------解密-------------------------
        byte[] ret = aes.invCipher(cipherText);//明文字节
        String val = new String(ret);
        System.out.println("invCipher cipherText : "+val);

    }
}