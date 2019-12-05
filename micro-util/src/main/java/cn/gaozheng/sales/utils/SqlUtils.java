package cn.gaozheng.sales.utils;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class SqlUtils {
    /**
     * 生成插入语句
     * @param tablename 表明
     * @param clazz 与数据库中字段一一对应的类
     * @param t 有数据的实体
     * @param <T> 数据实体类型 如 User
     */
    public static  <T> String getInsertSql(String tablename, Class<T> clazz, T t){
        //insert into table_name (column_name1,column_name2, ...) values (value1,value2, ...)
        String sql = "";
        Field[] fields = ReflectUtil.getFieldsDirectly(clazz, false);
        StringBuffer topHalf = new StringBuffer("insert into "+tablename+" (");
        StringBuffer afterAalf = new StringBuffer("values (");
        for (Field field : fields) {
            if ("ID".equals(field.getName()) || "id".equals(field.getName())){
                continue;   //id 自动生成无需手动插入
            }
            topHalf.append(UnderlineToHump.HumpToUnderline(field.getName()) + ",");
            Object obj = ReflectUtil.getFieldValue(t, field.getName());
            if (obj instanceof String) {
                afterAalf.append("'" + ReflectUtil.getFieldValue(t, field.getName()) + "',");
            } else if (field.getType() == java.util.Date.class) {
                java.util.Date date =  (java.util.Date) ReflectUtil.getFieldValue(t, field.getName());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                afterAalf.append("'" +simpleDateFormat.format(date) + "',");
            }
            else {
                afterAalf.append(ReflectUtil.getFieldValue(t, field.getName()) + ",");
            }
        }
        topHalf = new StringBuffer(StrUtil.removeSuffix(topHalf.toString(), ","));
        afterAalf = new StringBuffer(StrUtil.removeSuffix(afterAalf.toString(), ","));
        topHalf.append(") ");
        afterAalf.append(") ");
        sql = topHalf.toString() + afterAalf.toString();
        return sql;
    }

    /**
     * 生成更新语句
     * 必须含有id
     * 数据实体中 null 与 空字段不参与更新
     * @param tablename 数据库中的表明
     * @param clazz 与数据库中字段一一对应的类
     * @param t 有数据的实体
     * @param <T> 数据实体类型,如 User
     */
    public static  <T> String getUpdateSql(String tablename, Class<T> clazz, T t){
        //UPDATE table_name SET column_name1 = value1, column_name2 = value2, ... where ID=xxx
        //or
        //UPDATE table_name SET column_name1 = value1, column_name2 = value2, ... where id=xxx
        String sql = "";
        String id = ""; //保存id名：ID or id
        Field[] fields = ReflectUtil.getFieldsDirectly(clazz, false);
        sql = "update "+tablename+" set ";
        for (Field field : fields) {
            StringBuffer tmp = new StringBuffer();
            if ("ID".equals(field.getName()) || "id".equals(field.getName())){
                id = field.getName();
                continue;//更新的时候无需set id=xxx
            }
            if (ReflectUtil.getFieldValue(t, field.getName()) != null && ReflectUtil.getFieldValue(t, field.getName()) != "") {
                tmp.append( field.getName() + "=");
                if (ReflectUtil.getFieldValue(t, field.getName()) instanceof String) {
                    tmp.append( "'" + ReflectUtil.getFieldValue(t, field.getName()) + "',");
                } else {
                    tmp.append(ReflectUtil.getFieldValue(t, field.getName()) + ",");
                }
                sql += tmp;
            }
        }
        sql = StrUtil.removeSuffix(sql, ",") + " where " + id + "='" + ReflectUtil.getFieldValue(t, id)+"'";
        return sql;
    }


    /**
     * //根据数据库生成字段 例如 private Object a;
     * @param dbname 数据库名
     * @param tablename 表名称
     * @return 成员变量拼接后的字符串
     * @throws SQLException
     */
    public static String getPirvateObjectXxx(String dbname,String tablename) throws SQLException {
        Connection conn =  null;/*获取你的数据库连接*/;
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery("select COLUMN_NAME,COLUMN_TYPE from information_schema.COLUMNS where table_name = '"+tablename+"' and table_schema = '"+dbname+"'");
        StringBuffer sb=new StringBuffer();
        while (rs.next()) {
            sb.append("private Object "+rs.getObject(1)+";\n");
        }
        System.out.print(sb.toString());
        rs.close();
        stat.close();
        conn.close();
        return sb.toString();
    }
    public static String getFieldFormtByStr(String field){
        if (field == null) return "null";
        return String.format("'%s'",field);
    }

}
