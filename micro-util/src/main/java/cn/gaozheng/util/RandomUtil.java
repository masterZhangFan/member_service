package cn.gaozheng.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RandomUtil {

    private static volatile RandomUtil randomUtil = null;

    public static RandomUtil getInstance() {
        if (randomUtil == null) {
            synchronized (RandomUtil.class) {
                randomUtil = new RandomUtil();
            }
        }

        return randomUtil;
    }

    private static final String SYMBOL_STR = "0123456789";
    private static final Random SECURE_RANDOM = new SecureRandom();

    public String flexibleRandom(int length) {
        char[] chars = new char[length];
        for (int index = 0; index < chars.length; ++index) {
            chars[index] = SYMBOL_STR.charAt(SECURE_RANDOM.nextInt(SYMBOL_STR.length()));
        }
        return new String(chars);
    }

    public static int getNum(int start,int end) {
        return (int)(Math.random()*(end-start+1)+start);
    }
    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
    public static String getTel() { ;
        int index=getNum(0,telFirst.length-1);
        //手机号前三位
        String first=telFirst[index];
        String second=String.valueOf(getNum(1,888)+10000).substring(1);
        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
        return first+second+third;
    }

    public static Date randomDate(String beginDate, String endDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if(start.getTime() >= end.getTime()){
                return null;
            }
            long date = random(start.getTime(),end.getTime());
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }

}
