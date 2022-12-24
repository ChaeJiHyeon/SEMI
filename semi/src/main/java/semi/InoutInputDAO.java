package semi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.DBConn;

public class InoutInputDAO {
	Connection conn;
	PreparedStatement ps;
	
	InoutInputDAO(){
		conn = new DBConn().getConn();
	}
	
	public void close() {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
				ps = null;
				conn = null;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	
	public boolean inout_insert(FishVo vo) {
		if(conn == null) conn = new DBConn().getConn();
		
		boolean result = false;
		String sql = "";
		int cnt = 0;
		
		try {
			
			sql = "INSERT INTO inout_table(fishCode,combiCode,contCode,inoutCode,date,combiName,contName,fishName,inoutName,amt) VALUES(?,?,?,?,?,?,?,?,?,?)";
			
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getIo_fishCode());
			ps.setString(2, vo.getIo_combiCode());
			ps.setString(3, vo.getIo_contCode());
			ps.setString(4, vo.getIo_code());
			ps.setString(5, vo.getIo_date1());
			ps.setString(6, vo.getIo_combiName());
			ps.setString(7, vo.getIo_contName());
			ps.setString(8, vo.getIo_fishName());
			ps.setString(9, vo.getIo_name());
			ps.setString(10,vo.getIo_amt());
			
			cnt = ps.executeUpdate();
			if(cnt > 0) { 
				conn.commit();
				result = true;
				
			}
			else {
				conn.rollback();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	} 

	
	
	public boolean input_updateStock(FishVo vo) {
		if(conn == null) conn = new DBConn().getConn();
		
		boolean result = false;
		int ioCode = Integer.parseInt(vo.getIo_code());
		String sql = "";
		int cnt = 0;
				
		if(ioCode == 1) {
			sql = "UPDATE stock SET amt = amt + ? WHERE date = ? AND fishCode = ? AND combiCode = ?";
		}else {
			sql = "UPDATE stock SET amt = amt - ? WHERE date = ? AND fishCode = ? AND combiCode = ?";
		}
		
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getIo_amt());
			ps.setString(2, vo.getIo_date1());
			ps.setString(3, vo.getIo_fishCode().substring(0,6));
			ps.setString(4, vo.getIo_combiCode());
			cnt = ps.executeUpdate();
			if(cnt > 0) {
				conn.commit();
				result = true;
			}
			else { 
				conn.rollback(); 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		close();
		return result;
	}
	
	
}
