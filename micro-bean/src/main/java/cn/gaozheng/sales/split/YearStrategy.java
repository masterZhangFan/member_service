package cn.gaozheng.sales.split;

import java.text.SimpleDateFormat;
import java.util.Date;

public class YearStrategy implements Strategy {

    @Override
    public String convert(String tableName) {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY");
        StringBuilder sb=new StringBuilder(tableName);
        sb.append("_");
        sb.append(sdf.format(new Date()));
        return sb.toString();
    }

}
