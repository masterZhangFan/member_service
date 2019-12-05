package cn.gaozheng.sales.utils;

import java.util.Date;

public class EmptyUtil {

    public static boolean isNotEmpty(Integer value){
        if (value==null||value<=0){
            return false;
        }else{
            return true;
        }
    }
    public static boolean isNotEmpty(String value){
        if (value==null||value.length()==0||value.toLowerCase().equals("null")){
            return false;
        }else{
            return true;
        }
    }
    public static boolean isNotEmpty(Long value){
        if (value==null||value==0){
            return false;
        }else{
            return true;
        }
    }
    public static boolean isNotEmpty(Date value){
        if (value==null){
            return false;
        }else{
            return true;
        }
    }

    public static boolean isNotEmptyOrNegative(Double value){
        if (value==null||value<=0){
            return false;
        }else{
            return true;
        }
    }

    public static boolean isNotEmptyOrNegative(Integer value){
        if (value==null||value<=0){
            return false;
        }else{
            return true;
        }
    }
}
