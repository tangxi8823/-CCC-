package com.chord;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Iterator;

public class ChordUtils {
	//�˿ںŷ�Χ0-65355
	//����int
    public static int computeHash(String input) {
        byte[] value = null;
        int r=-1;
        try {
        	// �õ�MD5��instance
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            //md5�᷵��hashֵ��byte���飩 16*8=128λ
            byte[] init_code = md.digest(input.getBytes());
            //��byte[]ת����int
            r=Math.abs(bytesToInt(init_code));
            //intȡģ16,r[0,15]
            r=r%16;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return r;
    }
    
    //byte[]ת����int����λ��ǰ����λ�ں�
    private static int bytesToInt(byte[] src) {  
    	int value;    
        value = (int) ((src[0] & 0xFF)   
                | ((src[1] & 0xFF)<<8)   
                | ((src[2] & 0xFF)<<16)   
                | ((src[3] & 0xFF)<<24));  
        return value;  
    }

    //��ͻ���
	public static int checkCollision(int initIdx, byte[] isFixed) {
		int finalIdx=initIdx;
		while(isFixed[finalIdx]==1) {
			finalIdx++;
			//ȡģ
			finalIdx=finalIdx%16;
		}
		return finalIdx;
	}  
}
