package com.safety;

/**
 *@author 孟庆阔
 *@Time 2021年10月31日 下午4:19:47
 *
 */
public class MD5Test {

    public static void main(String[] args) {
        MD5 md = new MD5();
        String message = "孟庆阔 2021年10月31日 下午4:19:47";
        String result=md.start(message);//摘要求解
        System.out.println("明文: " + message);//明文
        System.out.println("密文: " +result);//密文
    }
}