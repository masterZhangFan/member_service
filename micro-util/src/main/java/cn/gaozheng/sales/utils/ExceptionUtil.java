package cn.gaozheng.sales.utils;

import org.springframework.dao.DataAccessException;

public class ExceptionUtil {
    public static String getExceptionDesc(Exception e){
        String msg;
        if (e instanceof DataAccessException){
//            msg = "数据访问异常";
            msg = e.getMessage();
        }else{
            msg = e.getMessage();
        }
        return msg;
    }
}
