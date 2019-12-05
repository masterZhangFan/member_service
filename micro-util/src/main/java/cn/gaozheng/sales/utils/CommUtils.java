package cn.gaozheng.sales.utils;

import cn.gaozheng.util.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CommUtils {
    public static boolean isPlateNumber(String plateNumber){
        if (plateNumber == null)
        {
            return false;
        }
        Pattern pattern = Pattern.compile(Constants.PATTERN_VEHICLE_NUMBER,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(plateNumber);
        boolean rs = matcher.matches();
        return rs;
    }
}
