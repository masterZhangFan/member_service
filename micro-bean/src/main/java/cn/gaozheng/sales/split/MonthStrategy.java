package cn.gaozheng.sales.split;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MonthStrategy implements Strategy {
    @Override
    public String convert(String tableName) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM");
        StringBuilder sb=new StringBuilder(tableName);
        sb.append("_");
        sb.append(sdf.format(new Date()));
        return sb.toString();
    }
}
