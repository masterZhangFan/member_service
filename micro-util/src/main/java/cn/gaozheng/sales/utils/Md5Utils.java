package cn.gaozheng.sales.utils;

public class Md5Utils {
    /**
     * 加密
     * @param originStr
     * @return
     */
    public static String MD5Encode(String originStr){
        // String s = new String(inStr);
        char[] a = originStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

    /**
     * 解密
     * @param md5
     * @return
     */
    public static String MD5Decode(String md5){
        char[] a = md5.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String k = new String(a);
        return k;
    }


}
