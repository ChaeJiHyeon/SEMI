package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	String driver = "com.mysql.cj.jdbc.Driver";
	
	String path = "jdbc:mysql://localhost:3306/semi";
	String user = "jiji";
	String password = "0000";
	private Connection conn;
	
	public DBConn() {
		try {
			Class.forName(driver);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		
		try {
			conn = DriverManager.getConnection(path, user, password);
			System.out.println("semi project DB 연결 완료!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void main(String[] args) {
		new DBConn().getConn();
	}
}
