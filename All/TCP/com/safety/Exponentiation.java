package com.safety;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *@author 孟庆阔
 *@Time 2021年10月31日 下午4:04:24
 *
 */
public class Exponentiation {
    /**
     * 超大整数超大次幂然后对超大的整数取模 (base ^ exponent) mod n
     *
     * @param base
     * @param exponent
     * @param n
     * @return
     */
    public BigInteger expMode(BigInteger base, BigInteger exponent, BigInteger n) {
        char[] binaryArray = new StringBuilder(exponent.toString(2)).reverse().toString().toCharArray();//将E或D转化为二进制，利用蒙哥马利算法进行处理得到最终得到结果。
        int r = binaryArray.length;//长度
        List<BigInteger> baseArray = new ArrayList<BigInteger>();
        BigInteger preBase = base;
        baseArray.add(preBase);
        for (int i = 0; i < r - 1; i++) {
            BigInteger nextBase = preBase.multiply(preBase).mod(n);//A[i]=A[I-1]*A[I-1]%N
            baseArray.add(nextBase);
            preBase = nextBase;
        }
        BigInteger a_w_b = this.multi(baseArray.toArray(new BigInteger[baseArray.size()]), binaryArray, n);
        return a_w_b.mod(n);
    }

    private BigInteger multi(BigInteger[] array, char[] bin_array, BigInteger n) {
        BigInteger result = BigInteger.ONE;
        for (int index = 0; index < array.length; index++) {
            BigInteger a = array[index];
            if (bin_array[index] == '0') {//当该二进制为零时不参与计算
                continue;
            }
            result = result.multiply(a);//先乘
            result = result.mod(n);//再取余
        }
        return result;
    }
}