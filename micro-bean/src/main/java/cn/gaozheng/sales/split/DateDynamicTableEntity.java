package cn.gaozheng.sales.split;

import cn.gaozheng.sales.anotation.table.TableSplit;
import tk.mybatis.mapper.entity.IDynamicTableName;

import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDynamicTableEntity implements IDynamicTableName {
    @Override
    public String getDynamicTableName() {
        StringBuilder tableName = new StringBuilder(this.getClass().getAnnotation(Table.class).name());
        TableSplit tableSplit = this.getClass().getAnnotation(TableSplit.class);
        SimpleDateFormat sdf = new SimpleDateFormat(tableSplit.strategy());
        String date = sdf.format(new Date());
        tableName.append(date);
        return tableName.toString();
    }

    public String getTableName() {
        return this.getClass().getAnnotation(Table.class).name();
    }

}
