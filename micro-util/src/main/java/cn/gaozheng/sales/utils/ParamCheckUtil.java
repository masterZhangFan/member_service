package cn.gaozheng.sales.utils;

import cn.gaozheng.sales.exception.SalesException;

import java.util.Date;

public class ParamCheckUtil {
    public static void checkPage(Integer pageIndex,Integer pageSize){
        if (!EmptyUtil.isNotEmpty(pageIndex)){
            pageIndex = 1;
            throw new SalesException("缺少pageIndex");
        }
        if (!EmptyUtil.isNotEmpty(pageSize)){
            pageSize = 10;
            throw new SalesException("缺少pageSize");
        }
    }
    public static void checkTime(Date fromTime, Date toTime) {
        if (fromTime==null){
            throw new SalesException("fromTime为空 或者格式不匹配yyyy-MM-dd HH:mm:ss");
        }
        if (toTime==null){
            throw new SalesException("toTime为空 或者格式不匹配yyyy-MM-dd HH:mm:ss");
        }
    }
    public static void paramNotNull(Object o,String msg){
        if (o==null){
            throw new SalesException(msg + "为空");
        }
    }
}
