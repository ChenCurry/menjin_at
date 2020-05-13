package cn.possible2dream.menjin_at.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbUtil {
	
	/**
	 * PC201804231430\SQLEXPRESS
	 */
	
	public static final String URL = "jdbc:sqlserver://172.30.47.14:57747; DatabaseName=PongeeESD6806_CN";
    public static final String USER = "it_readonly";//sa it_readonly
    public static final String PASSWORD = "1234@abcd";

    public static void main(String[] args) throws Exception {
        //1.加载驱动程序
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        //3.操作数据库，实现增删改查
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT top 10 SC_CardGuidNO FROM dbo.SC_AccessRecord where 1=1 order by SC_SerierNO desc");
        //如果有数据，rs.next()返回true
        while(rs.next()){
            System.out.println("卡号："+rs.getString("SC_CardGuidNO"));
            //System.out.println("卡号："+rs.getBigDecimal(columnIndex)("SC_CardGuidNO"));
        }
    }
}
