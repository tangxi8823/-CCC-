package com.safety;

/**
 * @author 孟庆阔
 * @Time 2021年10月30日 下午10:00:58
 *将int型转化为二进制
 *每八个一组转化为十进制
 *十进制转化为十六进制的字符串
 *将字符串每两个一组化为一个Byte
 */
public class Tranform {
    @SuppressWarnings("null")
    public static byte[] tranform2(int val){
        String s = Integer.toBinaryString(val);
        // System.out.println(s);
        char s1[] = s.toCharArray();
        // System.out.println(s1.length);
        char s2[] = new char[128];
        int i, n;
        n = 128 - s1.length;
        for (i = 0; i < n; i++) {
            s2[i] = '0';
        }
        int j;
        for (i = n, j = 0; i < 128; i++, j++) {
            s2[i] = s1[j];
        }
        // System.out.println(s2);
        int b[] = new int[16];
        char a1[][] = new char[16][8];
        int k = 0;
        for (i = 0; i < 16; i++) {
            for (j = 0; j < 8; j++) {
                a1[i][j] = s2[k++];
            }
        }
        int t1;
        char t2[] = new char[2];
        String b1[] = new String[16];
        for (i = 0; i < 16; i++) {
            t1 = tranform10(a1[i]);
            //System.out.println("t1:" + t1);
            t2 = tranform16(t1);
            //System.out.println(String.valueOf(t2));
            if (t2.length == 0) {
                b1[i] = "00";
            } else {
                b1[i] = String.valueOf(t2);
            }
        }
        StringBuilder builder = new StringBuilder();
        for (i = 0; i < 16; i++) {
            //System.out.println("b1["+i+"]:" + b1[i]);
            builder.append(b1[i]);
            //System.out.println(builder);
        }
        String re = builder.toString();
        re=re.replace(" ", "");
        //System.out.println(re);
        byte[] result = HexString2Bytes(re);
        //System.out.println(Bytes2HexString(result));
        return result;
    }

    // 将2进制转为10进制
    public static int tranform10(char a[]) {
        int i, l, sum;
        int c[] = { 128, 64, 32, 16, 8, 4, 2, 1 };
        l = a.length;
        sum = 0;
        for (i = 0; i < l; i++) {
            if (a[i] == '1') {
                sum += c[i];
            }
        }
        return sum;
    }

    // 将十进制转为16进制字符串
    public static char[] tranform16(int number) {
        int i = 0, k = 0;
        char[] S = new char[2];
        char[] S1 = new char[2];
        if (number == 0) {
            S1[0] = '0';
            S1[1] = '0';
        } else {
            while (number != 0) {
                int t = number % 16;
                if (t >= 0 && t < 10) {
                    S[i] = (char) (t + '0');
                    i++;
                } else {
                    S[i] = (char) (t + 'A' - 10);
                    i++;
                }
                number = number / 16;
            }
            if(i==1)
            {
                S[i++]='0';
                //System.out.println("S[i]=="+S[i-1]);
            }
            for (int j = i - 1; j >= 0; j--) {
                S1[k++] = S[j];
                //System.out.print(S[j]);
            }
        }
        return S1;
    }

    /**
     * 将指定byte数组以16进制的形式打印到控制台
     *
     * @param hint String
     * @param b    byte[]
     * @return void
     */
    public static void printHexString(String hint, byte[] b) {
        System.out.print(hint);
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            //System.out.print(hex.toUpperCase() + " ");
        }
        //System.out.println("");
    }

    /**
     *
     * @param b byte[]
     * @return String
     */
    public static String Bytes2HexString(byte[] b) {
        String ret = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            ret += " 0x" + hex.toUpperCase();
        }
        return ret;
    }

    /**
     * 将两个ASCII字符合成一个字节； 如："EF"–> 0xEF
     *
     * @param src0 byte
     * @param src1 byte
     * @return byte
     */
    public static byte uniteBytes(byte src0, byte src1) {
        byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 })).byteValue();
        _b0 = (byte) (_b0 << 4);
        byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 })).byteValue();
        byte ret = (byte) (_b0 ^ _b1);
        return ret;
    }

    /**
     * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" –> byte[]{0x2B, 0×44, 0xEF, 0xD9}
     *
     * @param src String
     * @return byte[]
     */
    public static byte[] HexString2Bytes(String src) {
        if (null == src || 0 == src.length()) {
            return null;
        }
        byte[] ret = new byte[src.length() / 2];
        byte[] tmp = src.getBytes();
        for (int i = 0; i < (tmp.length / 2); i++) {
            ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
        }
        return ret;
    }
}