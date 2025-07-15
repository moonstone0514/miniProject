package model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.sql.Statement;

//properties 파일에서 key로 value만 쏙쏙 뽑는 기능
import java.util.Properties;



public class DBUtil {
	
	//key로 value값 활용시에만 사용하는 API
	private static Properties dbInfo  = new Properties();
	
	Connection conn = null;
	ResultSet rs = null;
	Statement stmt = null;
	
	private DBUtil() {}
	
	static {
				
		try {
			dbInfo.load(new FileInputStream("dbinfo.properties"));
			Class.forName(dbInfo.getProperty("jdbc.driver"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static Connection getConnection() throws SQLException {
		
		return  DriverManager.getConnection(
				dbInfo.getProperty("jdbc.url"),
				dbInfo.getProperty("jdbc.id") ,
				dbInfo.getProperty("jdbc.pw") );
			
	}
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}

			if (stmt != null) {
				stmt.close();
				stmt = null;
			}

			if (conn != null) {
				conn.close();
				conn = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void close(Connection conn, Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}

			if (conn != null) {
				conn.close();
				conn = null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println();
	}
}
