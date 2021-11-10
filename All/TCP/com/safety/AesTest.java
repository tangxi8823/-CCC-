package com.safety;

import java.io.UnsupportedEncodingException;

/**
 *@author 孟庆阔
 *@Time 2021年10月31日 下午4:12:12
 *
 */
public class AesTest {
    public static void main(String[] args) throws UnsupportedEncodingException{
        int k=34131231;//k是密匙
        Aes aes = new Aes(k);//输入密匙创建Aes算法
        //--------加密-------------------------
        String plainText = "1wdq2w32242scsdvcszv3rfefeawfew";//明文
        byte[] buffer[]=aes.cipherone(plainText);
        //--------解密-------------------------

        String val = new String();
        val=aes.invCipherone(buffer);
        System.out.println("invCipher cipherText : "+val);

    }
}

