package semi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.DBConn;

public class InoutModifyDAO {
	Connection conn;
	PreparedStatement ps;
	
	InoutModifyDAO(){
		conn = new DBConn().getConn();
	}
	
	public void close() {
		try {
			if(ps != null) ps.close();
			if(conn != null ) conn.close();
			ps = null;
			conn = null;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 상세보기 io 수정
	public boolean update(FishVo vo) {
		if(conn != null) conn = new DBConn().getConn();
		
		int cnt = 0;
		boolean result = false;
		
		try {
			String sql = "UPDATE inout_table "
					+ "SET fishCode=?, combiCode=?, contCode=?, date=?, combiName=?, contName=?, fishName=?, amt=? "
					+ "WHERE idx = ?";
			
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getIo_fishCode());
			ps.setString(2, vo.getIo_combiCode());
			ps.setString(3, vo.getIo_contCode());
			ps.setString(4, vo.io_date1);
			ps.setString(5, vo.getIo_combiName());
			ps.setString(6, vo.getIo_contName());
			ps.setString(7, vo.getIo_fishName());
			ps.setString(8, vo.getIo_amt());
			ps.setString(9, vo.getIo_index());
			cnt = ps.executeUpdate();
			if(cnt > 0) {
				conn.commit();
				result = true;
				System.out.println("io 업데이트 완료");
				
			} else {
				conn.rollback();
			}
		}catch(Exception e) { e.printStackTrace(); }
		
		return result;
	}
	
	//상세보기 stock수정 
	// PreAmt = 수정하기 이전의 수량
	public boolean updateStock(FishVo vo,int PreAmt) {
		if(conn != null) conn = new DBConn().getConn();
		
		// 수정값의 차
		int io_amt = Integer.parseInt(vo.getIo_amt());
		int cha = (io_amt >= PreAmt) ? io_amt - PreAmt : PreAmt - io_amt;
		System.out.println("수정전 수량값 : " + PreAmt);
		System.out.println("수정후 수량값 : " + io_amt);
		System.out.println("수정전과 수정후의 수량차 : " + cha);
		int cnt = 0;
		String sql = "";
		boolean result = false;
		
		try {
			if(vo.io_code.equals("1")) {
				
				if(io_amt >= PreAmt) {
					sql = "UPDATE stock SET amt = amt + ? WHERE date = ? AND fishCode = ? AND combiCode = ?";
				}else if(io_amt < PreAmt) {
					sql = "UPDATE stock set amt = amt - ? WHERE date = ? AND fishCode = ? AND combiCode = ?";
				}
				
			} else if(vo.io_code.equals("2")) {
				if(io_amt >= PreAmt) {
					sql = "UPDATE stock SET amt = amt - ? WHERE date = ? AND fishCode = ? AND combiCode = ?";
				}else if(io_amt < PreAmt) {
					sql = "UPDATE stock set amt = amt + ? WHERE date = ? AND fishCode = ? AND combiCode = ?";
				}
				
			}
			
			System.out.println(sql);
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, cha+"");
			ps.setString(2, vo.getIo_date1());
			ps.setString(3, vo.getIo_fishCode().substring(0,6));
			ps.setString(4, vo.getIo_combiCode());
			cnt = ps.executeUpdate();
			
			if(cnt > 0) {
				conn.commit();
				System.out.println("stock 업데이트 완료");
				result = true;
			} else {
				conn.rollback();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 상세보기 io삭제
	public boolean delete(FishVo vo) {
		if(conn != null) conn = new DBConn().getConn();
		
		boolean result = false;
		int cnt = 0;
		try {
			String sql = "DELETE FROM inout_table WHERE idx = ?";
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getIo_index());
			cnt = ps.executeUpdate();
			
			if(cnt > 0) {
				conn.commit();
				result = true;
			}
			else conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//상세보기 삭제 stock 재고 수정
	public boolean deleteStock(FishVo vo) {
		if(conn != null) conn = new DBConn().getConn();
		
		boolean result = false;
		String sql = "";
		int cnt = 0;
		
		try {
			if(vo.io_code.equals("1")) {
				sql = "UPDATE stock SET amt = amt - ? WHERE date = ? AND fishCode = ? AND combiCode = ?";
			}else {
				sql = "UPDATE stock SET amt = amt + ? WHERE date = ? AND fishCode = ? AND combiCode = ?";
			}
			
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
			else conn.rollback();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
	public FishVo selectOne(String index) {
		if(conn != null) conn = new DBConn().getConn();
		FishVo vo = new FishVo();
		String sql = "";
		
		try {
			sql = "SELECT * FROM inout_table where idx = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, index);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				vo.setIo_fishCode(rs.getString("fishCode"));
				vo.setIo_combiCode(rs.getString("combiCode"));
				vo.setIo_contCode(rs.getString("contCode"));
				vo.setIo_code(rs.getString("inoutCode"));
				vo.setIo_date1(rs.getString("date"));
				vo.setIo_combiName(rs.getString("combiName"));
				vo.setIo_contName(rs.getString("contName"));
				vo.setIo_fishName(rs.getString("fishName"));
				vo.setIo_name(rs.getString("inoutName"));
				vo.setIo_amt(rs.getString("amt"));
				vo.setIo_index(rs.getString("idx"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return vo;
	}
}
