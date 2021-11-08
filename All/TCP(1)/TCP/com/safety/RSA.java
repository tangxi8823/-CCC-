package com.safety;

import java.math.BigInteger;


/**
 *@author 孟庆阔
 *@Time 2021年10月31日 下午3:59:26
 *
 */
public class RSA {
    public BigInteger[][] genKey(BigInteger p, BigInteger q) {//产生公钥、私钥
        BigInteger n = p.multiply(q);//n=p*q
        BigInteger fy = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));//subtract(); 相减;fy=(p-1)*(q-1);
        BigInteger e = new BigInteger("3889");//一个比n小且与fy互质的数，
        //(e,n)公钥
        BigInteger a = e;
        BigInteger b = fy;
        BigInteger[] rxy = new GCD().extGcd(a, b);
        BigInteger r = rxy[0];
        BigInteger x = rxy[1];
        BigInteger y = rxy[2];
        BigInteger d = x;
        //（d，n）私钥
        // 返回公钥 私钥
        return new BigInteger[][] { { n, e }, { n, d } };
    }
    /**
     * 加密
     *
     * @param m      被加密的信息转化成为大整数m
     * @param pubkey 公钥
     * @return
     */
    public BigInteger encrypt(BigInteger m, BigInteger[] pubkey) {
        BigInteger n = pubkey[0];
        BigInteger e = pubkey[1];

        BigInteger c = new Exponentiation().expMode(m, e, n);
        return c;
    }
    /**
     * 解密
     *
     * @param c
     * @param selfkey 私钥
     * @return
     */
    public BigInteger decrypt(BigInteger c, BigInteger[] selfkey) {
        BigInteger n = selfkey[0];
        BigInteger d = selfkey[1];

        BigInteger m = new Exponentiation().expMode(c, d, n);
        return m;
    }





}