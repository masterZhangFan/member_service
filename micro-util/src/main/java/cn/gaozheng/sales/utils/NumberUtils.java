package cn.gaozheng.sales.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberUtils {
    /**
     * 验证手机号正确性
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        if (phone == null) return false;
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            return isMatch;
        }

    }

    /**
     * 转换double
     * @param value 值
     * @param digit 位
     * @return
     */
    public static double getDouble(double value ,Integer digit){
        String formatString = "%."+digit+"f";
        formatString = String.format(formatString, value);
        return Double.parseDouble(formatString);
    }
    public static Double getDoubleByString(String value){
        Double result = null;
        try {
            result = Double.parseDouble(value);
        }
        catch (Exception ex)
        {

        }
        return result;
    }
    /*
     * 大于0
     * @param number
     * @return
     */
    public static boolean isNotNullAndGreaterThanZero(Integer number) {
        if (isNotNull(number) == true) {
            return number > 0;
        }
        return false;
    }

    /**
     * 是否为数组
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        if (str == null) return false;
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
    /**
     * 大于等于0
     *
     * @param number
     * @return
     */
    public static boolean isNotNullAndGreaterEqaulThanZero(Integer number) {
        if (isNotNull(number) == true) {
            return number > 0;
        }
        return false;

    }
    /**
     * 不为Null
     *
     * @param number
     * @return
     */
    public static boolean isNotNull(Integer number) {
        return number != null;
    }

    public static long genRand(long max,long min){
        Double randomDouble = Math.random();
        long radomNumber = min + (long)(randomDouble*(max -min + 1));
        return radomNumber;
    }
}

