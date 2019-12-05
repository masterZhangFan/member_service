package cn.gaozheng.sales.persistence;

import java.sql.*;
import java.util.List;

public class SqlObejct<T> {
    private static Connection conn = null;

    private static ResultSet rs;

    private static Statement stm;

    private SqlConnectionStr sqlConnectionStr = null;

    private static synchronized Connection getConn(SqlConnectionStr sqlConnectionStr){
        try {
            if (conn == null || conn.isClosed()){
                try {
                    Class.forName(sqlConnectionStr.getDriver());
                    conn = DriverManager.getConnection(sqlConnectionStr.getUrl(), sqlConnectionStr.getUsername(), sqlConnectionStr.getPassword());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        catch (SQLException ex){

        }

        return conn;
    }
    public SqlObejct(SqlConnectionStr sqlConnectionStr )
    {
        this.sqlConnectionStr = sqlConnectionStr;
    }
    //执行查询语句
    public List<T> query(String sql,Class<T> clazz) throws SQLException{
        List<T> lists = null;
        try {
            PreparedStatement pstmt = getConn(this.sqlConnectionStr).prepareStatement(sql);
            //建立一个结果集，用来保存查询出来的结果
            rs = pstmt.executeQuery();
            ResBeanUntil resBeanUntil = new ResBeanUntil();
            lists = resBeanUntil.getList(clazz,rs);
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lists;
    }

    public void execute(String sql) throws SQLException{
        PreparedStatement pstmt;
        try {
            pstmt = getConn(this.sqlConnectionStr).prepareStatement(sql);
            //建立一个结果集，用来保存查询出来的结果
            pstmt.execute();
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //关闭连接
    public void close(){
        try {
            if(conn != null) conn.close();
            if(stm != null) stm.close();
            if(rs != null) rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
