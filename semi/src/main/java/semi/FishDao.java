package semi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import jdbc.DBConn;

public class FishDao {
	Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	public FishDao() {
		try {
			conn = new DBConn().getConn();
			}
		catch(Exception e) {e.printStackTrace();}
	}
	
	public void close() {
		try {
			if(rs!=null) {
				rs.close(); //close만 하면 연결을 끊는 거라 null값이 되지 않는다.
				rs=null;
			}
			if(ps!=null) {
				ps.close();
				ps=null;
			}
			if(conn!=null) {
				conn.close();
				conn=null;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	// 입출고조회
	public List<FishVo> inout_select(Page pageVo, FishVo fVo){
		if(conn==null)
			conn = new DBConn().getConn();
		List<FishVo> list= new ArrayList<FishVo>();
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(cal.YEAR, -5);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today =formatter.format(cal.getTime());
		
		
		String preSql = "select count(i.idx) totSize from (select * from inout_table where date = ? and inoutCode = ?) as i where ";
		String tempSql = " ( contName like ? or combiName like ? or fishName like ? )";
		String sql = "";
		
		StringTokenizer st = new StringTokenizer(pageVo.getFindStr());
		String x = "";
		int i =0;
		
		try {
			if(st.countTokens()==0 | st.countTokens()==1) {
				sql = (preSql + tempSql);
				
				ps = conn.prepareStatement(sql);
				x = pageVo.getFindStr().trim();
				
				if(pageVo.getDate()=="" || pageVo.getDate()== null) {
					ps.setString(1,today);
					pageVo.setDate(today);
					
				}else {
					ps.setString(1, pageVo.getDate());
					
				}
				ps.setString(2, fVo.io_code);
				ps.setString(3, "%"+x+"%");
				ps.setString(4, "%"+x+"%");
				ps.setString(5, "%"+x+"%");
						
			}else {
				while(st.hasMoreTokens()) {
					preSql = (preSql+tempSql) + " or";
					st.nextToken();
				}
				sql = preSql.substring(0,preSql.length()-2);
				System.out.println(sql);
				ps = conn.prepareStatement(sql);
				st = new StringTokenizer(pageVo.getFindStr());
				if(pageVo.getDate()=="" || pageVo.getDate()== null) {
					ps.setString(1,today);
					pageVo.setDate(today);
					
				}else {
					ps.setString(1, pageVo.getDate());
					
				}
				
				ps.setString(2, fVo.io_code);
				
				while(st.hasMoreTokens()) {
					x = st.nextToken();
					ps.setString(i+3,  "%" + x + "%");
					ps.setString(i+4,  "%" + x + "%");
					ps.setString(i+5,  "%" + x + "%");
					i +=3;
					
				}
			}
	        rs = ps.executeQuery();
	        if(rs.next()) {
	        	int totSize = rs.getInt("totSize");
	        	System.out.println("totSize"+totSize);
	        	pageVo.setTotSize(totSize);
	        	pageVo.compute();
	        }
	        
	        preSql = "select i.* from (select * from inout_table where date = ? and inoutCode = ?) as i where";
	        tempSql = "(combiName like ? or contName like ? or fishName like ?)";
	        sql = "";
	        i =0 ;
	        System.out.println(sql);
	        st = new StringTokenizer(pageVo.getFindStr());
	        
	        if(st.countTokens()==0 | st.countTokens()==1) {
				sql = (preSql + tempSql) + " limit ? ,?";
				ps = conn.prepareStatement(sql);
				x = pageVo.getFindStr().trim();
				
				if(pageVo.getDate()=="" || pageVo.getDate()== null) {
					ps.setString(1,today);
					pageVo.setDate(today);
					
				}else {
					ps.setString(1, pageVo.getDate());
					
				}
				ps.setString(2, fVo.io_code);
				ps.setString(3, "%"+x+"%");
				ps.setString(4, "%"+x+"%");
				ps.setString(5, "%"+x+"%");
				ps.setInt(6, pageVo.getStartNo());
				ps.setInt(7, pageVo.getListSize());
						
			}else {
				while(st.hasMoreTokens()) {
					preSql = (preSql+tempSql) + " or";
					st.nextToken();
				}
				sql = preSql.substring(0,preSql.length()-2) +" limit ?, ?";
				
				ps = conn.prepareStatement(sql);
				st = new StringTokenizer(pageVo.getFindStr());
				if(pageVo.getDate()=="" || pageVo.getDate()== null) {
					ps.setString(1,today);
					pageVo.setDate(today);
					
				}else {
					ps.setString(1, pageVo.getDate());
					
				}
				
				ps.setString(2, fVo.io_code);
				
				while(st.hasMoreTokens()) {
					x = st.nextToken();
					ps.setString(i+3,  "%" + x + "%");
					ps.setString(i+4,  "%" + x + "%");
					ps.setString(i+5,  "%" + x + "%");
					i +=3;
					
				}
				ps.setInt(i+3, pageVo.getStartNo());
				ps.setInt(i+4, pageVo.getListSize());
			} 
	        	
			rs=ps.executeQuery();

			while(rs.next()) {
				FishVo vo= new FishVo();
				vo.setIo_index(rs.getString("idx"));
				vo.setIo_date1(rs.getString("date"));
				vo.setIo_combiCode(rs.getString("combiCode"));
				vo.setIo_combiName(rs.getString("combiName"));
				vo.setIo_contCode(rs.getString("contCode"));
				vo.setIo_contName(rs.getString("contName"));
				vo.setIo_fishCode(rs.getString("fishCode"));
				vo.setIo_fishName(rs.getString("fishName"));
				vo.setIo_amt(rs.getString("amt"));

				list.add(vo);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		close();
		
		return list;
	}

	// 입출고현황 조회
	public List<FishVo> stats_select(Page pageVo, FishVo fVo){
		if(conn==null)
			conn = new DBConn().getConn();
		List<FishVo> list= new ArrayList<FishVo>();
		String sql="";
		try { 
			if(fVo.getIo_type().equals("월별")) {
			   sql= "select count(c.tot) totSize from(select count(Date_Format(date, '%Y-%m')) as tot from inout_table";
			   sql += type_detail(fVo);
			   sql+= " group by Date_Format(date, '%Y-%m') order by Date_Format(date, '%Y-%m')) as c";
			}else if(fVo.getIo_type().equals("일자별")) {
				sql="select count(c.tot) totSize from(select count(date) as tot from inout_table";
				sql+=type_detail(fVo);
				sql+=" group by date order by date) as c";
			}else {
				sql="select count(c.tot) totSize from(select count(fishName) as tot from inout_table";
				sql+=type_detail(fVo);
				sql+=" GROUP BY fishName ORDER BY fishName) as c";
			}
			System.out.println(sql);
		  //sdfa
		  ps = conn.prepareStatement(sql); 
		  rs = ps.executeQuery(); 
		  rs.next();
		  int totSize = rs.getInt("totSize");
		  pageVo.setTotSize(totSize); 
		  pageVo.compute(); 
		  
			if(fVo.getIo_type().equals("월별")) {
				sql="select Date_Format(date, '%Y-%m'), sum(amt) from inout_table";
				sql += type_detail(fVo);
				sql+= " group by Date_Format(date, '%Y-%m') order by Date_Format(date, '%Y-%m')";
			}else if(fVo.getIo_type().equals("일자별")) {
				sql="select date, sum(amt) from inout_table";
				sql+=type_detail(fVo);
				sql+=" group by date order by date";
			}else {
				sql="select sum(amt), fishName from inout_table";
				sql+=type_detail(fVo);
				sql+=" GROUP BY fishName ORDER BY fishName";
			}
			sql+= " limit ?, ?";
			
			ps = conn.prepareStatement(sql);
	        ps.setInt(1,  pageVo.getStartNo());
	        ps.setInt(2, pageVo.getListSize());
			rs=ps.executeQuery();
			   
			while(rs.next()) {
				FishVo vo= new FishVo();
				
				if(fVo.getIo_type().equals("월별")) {
					vo.setIo_type(rs.getString("Date_Format(date, '%Y-%m')"));
					vo.setIo_amt(rs.getString("sum(amt)"));
				}else if(fVo.getIo_type().equals("일자별")) {
					vo.setIo_type(rs.getString("date"));
					vo.setIo_amt(rs.getString("sum(amt)"));
				}else {
					vo.setIo_type(rs.getString("fishName"));
					vo.setIo_amt(rs.getString("sum(amt)"));	
				}
				list.add(vo);
			}	
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		close();
		
		return list;
	}
	
	// 타입별 쿼리
	public String type_detail(FishVo fVo) {
		String sql = "";
		if(fVo.getIo_code()!=null) {
			sql+=" where inoutCode = '"+fVo.getIo_code()+"'";
		}
		if(fVo.getIo_date1()!=null && fVo.getIo_date2()!=null) {
			sql+=" and date BETWEEN '"+fVo.getIo_date1()+"' and '"+fVo.getIo_date2()+"'";
		}
		if(fVo.getIo_combiName()!="") {
			sql+=" and combiName = '"+fVo.getIo_combiName()+"'";
		}
		if(fVo.getIo_contName()!="") {
			sql+=" and contName = '"+fVo.getIo_contName()+"'";
		}
		if(fVo.getIo_fishName()!="") {
			sql+=" and fishName = '"+fVo.getIo_fishName()+"'";
		}
		return sql;
	}
//
//	  public Page pageCheck(Page pageVo, FishVo f){ 
//		  try {
//			  String sql="";
//			  if(f.getIo_type().equals("월별")) {
//				   sql= "select count(c.tot) totSize from(select count(Date_Format(date, '%Y-%m')) as tot from inout_table";
//				   sql += type_detail(f);
//				   sql+= " group by Date_Format(date, '%Y-%m') order by Date_Format(date, '%Y-%m')) as c";
//				}else if(f.getIo_type().equals("일자별")) {
//					sql="select count(c.tot) totSize from(select count(date) as tot from inout_table";
//					sql+=type_detail(f);
//					sql+=" group by date order by date) as c";
//				}else {
//					sql="select count(c.tot) totSize from(select count(fishName) as tot from inout_table";
//					sql+=type_detail(f);
//					sql+=" GROUP BY fishName ORDER BY fishName) as c";
//				}
//			  //sdfa
//			  ps = conn.prepareStatement(sql); 
//			  rs = ps.executeQuery(); 
//			  rs.next();
//			  int totSize = rs.getInt("totSize");
//			  pageVo.setTotSize(totSize); 
//			  pageVo.compute(); 
//			  
//			  } catch(Exception e){e.printStackTrace();}
//		  return pageVo;
//	  }
	  
}
