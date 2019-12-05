package cn.gaozheng.sales.persistence;

public class SqlConnectStrFactory {
    public static SqlConnectionStr getEMEZSConnctionStr(){
        SqlConnectionStr sqlConnectionStr = new SqlConnectionStr();
        sqlConnectionStr.setName("emeszs");
        sqlConnectionStr.setDbType("oracle");
        sqlConnectionStr.setDriver("oracle.jdbc.driver.OracleDriver");
//        sqlConnectionStr.setUrl("jdbc:oracle:thin:@//220.182.56.214:1521/EMESDB");//测试数据库
        sqlConnectionStr.setUrl("jdbc:oracle:thin:@//10.3.6.254:1521/EMESDB");//正式数据库
//        sqlConnectionStr.setUrl("jdbc:oracle:thin:@//17mk042095.iok.la:24867/EMESDB");
        sqlConnectionStr.setUsername("emeszs");
        sqlConnectionStr.setPassword("emeszs");
        return sqlConnectionStr;
    }
    public static SqlConnectionStr getBulkSqlConnectionsStr(){
        SqlConnectionStr sqlConnectionStr = new SqlConnectionStr();
        sqlConnectionStr.setName("散装数据同步");
        sqlConnectionStr.setDbType("com.microsoft.sqlserve");
        sqlConnectionStr.setDriver("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        sqlConnectionStr.setUrl("jdbc:sqlserver://192.168.40.100:1433");
//        sqlConnectionStr.setUrl("jdbc:sqlserver://17mk042095.iok.la:16017");
        sqlConnectionStr.setUsername("sa");
        sqlConnectionStr.setPassword("Gzdbsa12345");
        return sqlConnectionStr;
    }
}
