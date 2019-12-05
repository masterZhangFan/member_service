package cn.gaozheng.sales.provider.sql;

public interface SelectSql {

    void addSelect(String columns);
    void addFrom();
    void addFrom(String tableEnd);
    void addJoin();
    void addWhere(String condition);
    void addWhereOutStack(String condition);

    String getSqlString();

}
