/*
 * - DA0 권장설계 : table 당 1:1roqkf
 * 
 * 24시간 365일 실행되는 서버의 실행 코드라 간주
 * - 요청 수와 무관하게 최초 단 한번만 1회성으로 공유 자원 초기화 하는 공통 코드로 간주
 * - db의 driver 로징 로직
 * - 시스템 다운 방지를 위한 리소스 최적화
 *   : Connection 수는 절대 제한(유한 자원)
 *   - web server 내부에서 설정으로 db server 시스템 동시 접속수 제어 예정
 *   - connection pool 기법 (CP)
 *   - Connection 제공, 회수하는 주체 필요(javax.sql.DataSource가 할 예정)
 *   
 *   결론
 *   	- driver 로딩 한번만 실행 보장
 *   	- Connection 객체 반환하는 로직
 *   	- DB 설정 정보는 별도의 key로 db 접속 정보 매핑해서 properties 파일로 분리
 *   		: key는 절대 중복되어서는 안된다.
 *   - 참고 
 *   	: 설정 정보를 코드에서 분리하는 우너조가 JDBC라고 간주
 *   	- Spring 에서 default 설정 파일
 *   
 */


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
	private static Properties dbInfo  = new Properties();;
	
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
		//
		
		return  DriverManager.getConnection(dbInfo.getProperty("jdbc.url"),dbInfo.getProperty("jdbc.id") ,dbInfo.getProperty("jdbc.pw") );
			
	}
	//자원 반환 필수 (query)
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
	
	//자원 반환 필수(Insert, update/ delete)
	
	//자원 반환 필수 (query)
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
