package com.chord;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Iterator;

public class ChordUtils {
	//端口号范围0-65355
	//返回int
    public static int computeHash(String input) {
        byte[] value = null;
        int r=-1;
        try {
        	// 得到MD5的instance
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            //md5会返回hash值（byte数组） 16*8=128位
            byte[] init_code = md.digest(input.getBytes());
            //把byte[]转换成int
            r=Math.abs(bytesToInt(init_code));
            //int取模16,r[0,15]
            r=r%16;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return r;
    }
    
    //byte[]转换成int，低位在前，高位在后
    private static int bytesToInt(byte[] src) {  
    	int value;    
        value = (int) ((src[0] & 0xFF)   
                | ((src[1] & 0xFF)<<8)   
                | ((src[2] & 0xFF)<<16)   
                | ((src[3] & 0xFF)<<24));  
        return value;  
    }

    //冲突解决
	public static int checkCollision(int initIdx, byte[] isFixed) {
		int finalIdx=initIdx;
		while(isFixed[finalIdx]==1) {
			finalIdx++;
			//取模
			finalIdx=finalIdx%16;
		}
		return finalIdx;
	}  
}
