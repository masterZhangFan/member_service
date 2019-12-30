package cn.gaozheng.sales.utils;

import org.springframework.util.DigestUtils;
import sun.misc.BASE64Encoder;

public class SaleStringUtils {

    private static final String slat = "&%5123***&&%%$$#@";
    public static String getMd5(String str)
    {
        String base = str +"/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    public static String base64Encode(String str) {
        BASE64Encoder encoder = new BASE64Encoder();
        try{
            byte[] textByte = str.getBytes("UTF-8");
            String encodedText = encoder.encode(textByte);
            return encodedText;
        }
        catch (Exception ex) {

        }
        return null;
    }
    public static byte[] base64Decode(String str) {
        byte[] bt = null;
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            bt = decoder.decodeBuffer(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bt;
    }
}
