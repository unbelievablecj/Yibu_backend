package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 用于获取数据库连接，注意centos7默认使用mariadb而不是mysql
 * @author unbel
 *
 */
public class DBUtil {

	private static final String URL="jdbc:mariadb://localhost:3306/Yibu?user=root&password=chenjing5114"
            + "&useUnicode=true&characterEncoding=UTF8&useSSL=false";
	private  Connection conn=null;
	
	public Connection getConnection(){
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn=DriverManager.getConnection(URL);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
