package cn.gaozheng.sales.anotation.table;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface TableSplit {
    //是否分表
    boolean split() default true;
    //表名
    String value();

    //获取分表策略,如YYYY or yyyyMM
    String strategy();
}
