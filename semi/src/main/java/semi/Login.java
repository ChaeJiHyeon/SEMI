package semi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdbc.DBConn;

public class Login {
	public boolean login(String id, String pw) {
		boolean b = false;
		try {
			Connection conn = new DBConn().getConn();
			String sql = "select * from user where id=? and pw=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				b = true;
			}
			ps.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(b);
		return b;
	}
	/*
	 * public static void main(String[] args) { Login l = new Login();
	 * l.login("hong", "1111"); }
	 */
}
