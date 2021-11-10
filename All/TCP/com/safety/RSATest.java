package com.safety;

import java.math.BigInteger;

/**
 *@author 孟庆阔
 *@Time 2021年10月31日 下午4:06:16
 *
 */
public class RSATest {
    public static void main(String[] args) {
        // 公钥私钥中用到的两个大质数p,q,不要修改'''
        BigInteger p = new BigInteger("997");
        BigInteger q = new BigInteger("1009");

        RSA rsa = new RSA();
        // 生成公钥私钥'''
        // pubkey, selfkey = gen_key(p, q)
        BigInteger[][] keys = rsa.genKey(p, q);
        BigInteger[] pubkey = keys[0];
        BigInteger[] selfkey = keys[1];

        int i = 0;
        for(;i<pubkey.length;i++){
            System.out.println(String.valueOf(pubkey[i]));
        }
/*        String c = String.valueOf(pubkey);
        System.out.println(c);*/



/*        // 需要被加密的信息转化成数字，长度小于秘钥n的长度，如果信息长度大于n的长度，那么分段进行加密，分段解密即可。'''
        BigInteger m = new BigInteger("123456");

        // 信息加密'''
        BigInteger c = rsa.encrypt(m, pubkey);
        System.out.println("密文：" + c);
        // 信息解密'''
        BigInteger d = rsa.decrypt(c, selfkey);
        System.out.println("被解密后信息：" + d);*/
    }
}