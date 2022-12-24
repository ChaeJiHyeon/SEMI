package semi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import jdbc.DBConn;

public class StockDao {
   Connection conn;
   PreparedStatement ps;
   ResultSet rs;
   StockVo sVo;

   public StockDao() {
      try {
         conn = new DBConn().getConn();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   // 재고변동표 클릭시 바로 뿌려질 데이터
   public List<StockVo> change_select_all() {
      if (conn == null)
         conn = new DBConn().getConn();
      List<StockVo> list = new ArrayList<StockVo>();
      sVo = new StockVo();
      try {
         String sql = "select s.date as mDate, s.a as sAmt, i.iAmt, i.oAmt from (select date, sum(amt) a from stock where date between ? and ? group by date) as s left join "
                  + " (select date, sum(if(inoutCode=1, amt, 0)) as iAmt, sum(if(inoutCode=2, amt, 0)) as oAmt from inout_table where date between ? and ? group by date) as i on s.date = i.date";
         
         ps = conn.prepareStatement(sql);
         ps.setString(1, sVo.getStock_date1());
         ps.setString(2, sVo.getStock_date2());
         ps.setString(3, sVo.getStock_date1());
         ps.setString(4, sVo.getStock_date2());

         rs = ps.executeQuery();
         while(rs.next()) {
            String date = (String)rs.getString("mDate");
            double iAmt = Double.parseDouble(rs.getString("iAmt"));
            double oAmt = Double.parseDouble(rs.getString("oAmt"));
            double sAmt = Double.parseDouble(rs.getString("sAmt"));
            System.out.println(date);

            sVo = new StockVo();
            sVo.setStock_date(date);
            sVo.setiAmt(iAmt);
            sVo.setoAmt(oAmt);
            sVo.setsAmt(sAmt);

            list.add(sVo);
         }
         
      } catch (Exception e) {
         e.printStackTrace();
      }
      close();
      return list;
   }

   // 상세 검색
   public List<StockVo> change_select(StockVo sVo) {
      if (conn == null)
         conn = new DBConn().getConn();
      List<StockVo> list = new ArrayList<StockVo>();
      System.out.println(sVo.getStock_fishName());
      String sql = "";
      System.out.println(sVo.getStock_type());
      try {
         if (sVo.getStock_type().equals("월별")) {
            String date1=sVo.getStock_date1();
               String date2=sVo.getStock_date2();
               String str = sVo.getStock_fishName();
               String fishCode = str.substring(str.length()-6, str.length());
               int cntDate = Integer.parseInt(date2.substring(5, 7)) - Integer.parseInt(date1.substring(5,7));
               System.out.println(fishCode);
               
               if(cntDate==0) {
                  sql="select s.month, s.sAmt, i.iAmt, i.oAmt from (select month(date) month, fishCode, sum(amt) sAmt from stock where date = '"+date2+"' and fishCode = '"+fishCode+"' group by date) as s join "
                        + " (select month(date) imonth ,sum(if(inoutCode=1, amt, 0)) as iAmt, sum(if(inoutCode=2, amt, 0)) as oAmt from inout_table where date between '"+date1+"' and '"+date2+"' and subString(fishCode,1,6)='"+fishCode+"' group by month(date)) as i "
                        + " on s.month = i.imonth";
                  ps = conn.prepareStatement(sql);

                  System.out.println(sql);
                  rs = ps.executeQuery();

                  StockVo vo = new StockVo();
                  if (rs.next()) {
                     vo.setStock_type("월별");
                     vo.setStock_date(rs.getString("month"));
                     vo.setiAmt(Double.parseDouble(rs.getString("iAmt")));
                     vo.setoAmt(Double.parseDouble(rs.getString("oAmt")));
                     vo.setsAmt(Double.parseDouble(rs.getString("sAmt")));
                  
                     list.add(vo);
                  }
               }
               
               if(cntDate>=1) {
                  
                  sql="select s.month, s.sAmt, i.iAmt, i.oAmt from (select month(date) month, fishCode, sum(amt) sAmt from stock where date = LAST_DAY('"+date1+"') and fishCode = '"+fishCode+"' group by date) as s join "
                        + " (select month(date) imonth ,sum(if(inoutCode=1, amt, 0)) as iAmt, sum(if(inoutCode=2, amt, 0)) as oAmt from inout_table where date between '"+date1+"' and LAST_DAY('"+date1+"') and subString(fishCode,1,6)='"+fishCode+"' group by month(date)) as i "
                        + " on s.month = i.imonth";
                  ps = conn.prepareStatement(sql);

                  System.out.println(sql);
                  rs = ps.executeQuery();

                  StockVo vo = new StockVo();
                  if (rs.next()) {
                     vo.setStock_type("월별");
                     vo.setStock_date(rs.getString("month"));
                     vo.setiAmt(Double.parseDouble(rs.getString("iAmt")));
                     vo.setoAmt(Double.parseDouble(rs.getString("oAmt")));
                     vo.setsAmt(Double.parseDouble(rs.getString("sAmt")));
                  
                     list.add(vo);
                  }
                     
                     if(cntDate>1) {
                        for(int i =1; i<cntDate;i++) {
                           sql="select s.smonth, s.sAmt, i.iAmt, i.oAmt from (select month(date) as smonth, fishCode, sum(amt) sAmt from stock where date = LAST_DAY(DATE_FORMAT(DATE_ADD('"+date1+"', INTERVAL "+i+" MONTH),'%Y-%m-01')) and fishCode = '"+fishCode+"' group by date) as s join "
                                 + " (select month(date) as imonth ,sum(if(inoutCode=1, amt, 0)) as iAmt, sum(if(inoutCode=2, amt, 0)) as oAmt from inout_table where date between DATE_FORMAT(DATE_ADD('"+date1+"', INTERVAL "+i+" MONTH),'%Y-%m-01') and LAST_DAY(DATE_FORMAT(DATE_ADD('"+date1+"', INTERVAL "+i+" MONTH),'%Y-%m-01')) and subString(fishCode,1,6)='"+fishCode+"' group by month(date)) as i"
                                 + " on s.smonth = i.imonth";
                           ps = conn.prepareStatement(sql);
                           
                           System.out.println(sql);
                           rs = ps.executeQuery();
                           vo = new StockVo();
                           while (rs.next()) {
                              vo.setStock_type("월별");
                              vo.setStock_date(rs.getString("smonth"));
                              vo.setiAmt(Double.parseDouble(rs.getString("iAmt")));
                              vo.setoAmt(Double.parseDouble(rs.getString("oAmt")));
                              vo.setsAmt(Double.parseDouble(rs.getString("sAmt")));
                           
                              list.add(vo);
                              System.out.println(Arrays.asList(list));
                           }
                        }
                     }
                     sql="select s.smonth, s.sAmt, i.iAmt, i.oAmt from (select month(date) smonth, fishCode, sum(amt) sAmt from stock where date = '"+date2+"' and fishCode = '"+fishCode+"' group by date) as s join "
                           + " (select month(date) imonth ,sum(if(inoutCode=1, amt, 0)) as iAmt, sum(if(inoutCode=2, amt, 0)) as oAmt from inout_table where date between DATE_FORMAT('"+date2+"' ,'%Y-%m-01') and '"+date2+"' and subString(fishCode,1,6)='"+fishCode+"' group by month(date)) as i "
                           + " on s.smonth = i.imonth";
                     ps = conn.prepareStatement(sql);

                     System.out.println(sql);
                     rs = ps.executeQuery();
                     vo = new StockVo();
                     if (rs.next()) {
                        vo.setStock_type("월별");
                        vo.setStock_date(rs.getString("smonth"));
                        vo.setiAmt(Double.parseDouble(rs.getString("iAmt")));
                        vo.setoAmt(Double.parseDouble(rs.getString("oAmt")));
                        vo.setsAmt(Double.parseDouble(rs.getString("sAmt")));
                     
                        list.add(vo);
                     }
                  
               }
         } else if (sVo.getStock_type().equals("일별")) {   
            sql = "select s.fishCode, s.date as mDate, s.sAmt, i.iAmt, i.oAmt from (select date, sum(amt) sAmt, fishCode from stock where ";
            sql += type_detail(sVo);
            sql += type_detail2(sVo);
            sql += " as i on s.date = i.date";
            
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()) {
               sVo = new StockVo();
               String date = (String)rs.getString("mDate");

               
               double iAmt = 0.0;
               double oAmt = 0.0;
               double sAmt = Double.parseDouble(rs.getString("sAmt"));
               if((rs.getString("iAmt"))!=null) 
                  iAmt = Double.parseDouble(rs.getString("iAmt"));

               if((rs.getString("oAmt"))!=null) 
                  oAmt = Double.parseDouble(rs.getString("oAmt"));

               
               sVo = new StockVo();
               sVo.setStock_date(date);
               sVo.setiAmt(iAmt);
               sVo.setoAmt(oAmt);
               sVo.setsAmt(sAmt);

               list.add(sVo);
            }
         }

      } catch (Exception ex) {
         ex.printStackTrace();
      }
      close();
      return list;
   }
   
    public String type_detail(StockVo sVo) { 
       String sql = "";
       if(sVo.getStock_date1()!=null && sVo.getStock_date2()!=null) { 
          //sql += " s.date BETWEEN '" + sVo.getStock_date1() + "' and '" + sVo.getStock_date2()+"' "; 
          sql += "date between '" + sVo.getStock_date1() + "' and '" + sVo.getStock_date2() + "' ";
       } 
       if(sVo.getStock_fishName()!=null) {
          String str = sVo.getStock_fishName();
          String fc = str.substring(str.length()-6, str.length());
          System.out.println(fc);
          sql += " and fishCode = '" + fc + "' group by date) as s left join (select date, sum(if(inoutCode=1, amt, 0)) as iAmt, sum(if(inoutCode=2, amt, 0)) as oAmt from inout_table where "; 
       } 
       return sql; 
   }
    
    public String type_detail2(StockVo sVo) { 
       String sql = "";
       if(sVo.getStock_date1()!=null && sVo.getStock_date2()!=null) { 
          //sql += " s.date BETWEEN '" + sVo.getStock_date1() + "' and '" + sVo.getStock_date2()+"' "; 
          sql += "date between '" + sVo.getStock_date1() + "' and '" + sVo.getStock_date2() + "' ";
       } 
       if(sVo.getStock_fishName()!=null) {
          String str = sVo.getStock_fishName();
          String fc = str.substring(str.length()-6, str.length());
          System.out.println(fc);
          sql += " and subString(fishCode,1,6)='" + fc + "' group by date) "; 
       } 
       return sql; 
   }
    
    public String type_detail3(StockVo sVo) { 
       String sql = "";
       if(sVo.getStock_date1()!=null && sVo.getStock_date2()!=null) { 
          //sql += " s.date BETWEEN '" + sVo.getStock_date1() + "' and '" + sVo.getStock_date2()+"' "; 
          sql += "date between '" + sVo.getStock_date1() + "' and '" + sVo.getStock_date2() + "' ";
       } 
       if(sVo.getStock_fishName()!=null) {
          String str = sVo.getStock_fishName();
          String fc = str.substring(str.length()-6, str.length());
          System.out.println(fc);
          sql += " and fishCode = '" + fc + "' group by date) as s left join (select date, sum(if(inoutCode=1, amt, 0)) as iAmt, sum(if(inoutCode=2, amt, 0)) as oAmt from inout_table where "; 
       } 
       return sql; 
   }
   
   public void close() {
      try {
         if (rs != null) {
            rs.close();
            rs = null;
         }
         if (ps != null) {
            ps.close();
            ps = null;
         }
         if (conn != null) {
            conn.close();
            conn = null;
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public List<StockVo> select(Page pageVo){
		if(conn==null) conn = new DBConn().getConn();
		List<StockVo> list = new ArrayList<>();
		Date now = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(now);
		cal.add(cal.YEAR, -5);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String today =formatter.format(cal.getTime());
		
		String preSql = "select count(*) totSize from stock where ";
		String tempSql = " ( contName like ? or combiName like ? or fishName like ? )";
		String sql = "";
		
		StringTokenizer st = new StringTokenizer(pageVo.getFindStr());
		String x = "";
		int i = 0;
		
		try {
		
		if(st.countTokens()==0 | st.countTokens()==1) {
			sql = (preSql+tempSql) + " and date = ?";
			ps = conn.prepareStatement(sql);
			x= pageVo.getFindStr();
			ps.setString(1, "%"+x+"%");
			ps.setString(2, "%"+x+"%");
			ps.setString(3, "%"+x+"%");
			if(pageVo.getDate()=="") {
				ps.setString(4, today);
				pageVo.setDate(today);
			}else {
				ps.setString(4, pageVo.getDate());
			}
			
		}else {
			while(st.hasMoreTokens()) {
				preSql = (preSql + tempSql) + " and";
				st.nextToken();
			}
			sql = preSql.substring(0, preSql.length()-3)+" and date = ?";
			
			ps = conn.prepareStatement(sql);
			st = new StringTokenizer(pageVo.getFindStr());
			while(st.hasMoreTokens()) {
				x = st.nextToken();
				ps.setString(1+i, "%" + x + "%");
				ps.setString(2+i, "%" + x + "%");
				ps.setString(3+i, "%" + x + "%");
				i += 3;
			}
			if(pageVo.getDate()=="") {
				ps.setString(4, today);
			}else {
				ps.setString(i+1, pageVo.getDate());
			}
		}
		
		
		
		
		rs = ps.executeQuery();

		if(rs.next()) {
			int totSize = rs.getInt("totSize");
			pageVo.setTotSize(totSize);
			pageVo.compute();
		}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		preSql = "select * from stock where ";
		tempSql = " (contName like ? or combiName like ? or fishName like ? )";
		sql = "";
		
		x = "";
		i = 0;
		
		st = new StringTokenizer(pageVo.getFindStr());
		
		try {
			if(st.countTokens()==0 | st.countTokens()==1) {
				sql = (preSql+tempSql) + " and date = ? limit ?, ?";
				ps = conn.prepareStatement(sql);
				x=pageVo.getFindStr();
				ps.setString(1, "%"+x+"%");
				ps.setString(2, "%"+x+"%");
				ps.setString(3, "%"+x+"%");
				if(pageVo.getDate()=="") {
					ps.setString(4, today);
				}else {
					ps.setString(4, pageVo.getDate());
				}
				ps.setInt(5, pageVo.getStartNo());
				ps.setInt(6, pageVo.getListSize());
				
				
			}else {
				while(st.hasMoreTokens()) {
					preSql = (preSql + tempSql) + " and";
					st.nextToken();
				}
				sql = preSql.substring(0, preSql.length()-3)+" and date = ? limit ?, ?";
				ps = conn.prepareStatement(sql);
				
				st = new StringTokenizer(pageVo.getFindStr());
				while(st.hasMoreTokens()) {
					x = st.nextToken();
					ps.setString(1+i, "%" + x + "%");
					ps.setString(2+i, "%" + x + "%");
					ps.setString(3+i, "%" + x + "%");
					i += 3;
				}
				if(pageVo.getDate()=="") {
					ps.setString(4, today);
				}else {
					ps.setString(i+1, pageVo.getDate());
				}
				ps.setInt(i+2, pageVo.getStartNo());
				ps.setInt(i+3, pageVo.getListSize());
			}
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				StockVo vo = new StockVo();
				vo.setStock_combiCode(Integer.parseInt(rs.getString("combiCode")));
				vo.setStock_contCode(Integer.parseInt(rs.getString("contCode")));
				vo.setStock_fishCode(rs.getString("fishCode"));
				vo.setStock_date1(rs.getString("date"));
				vo.setStock_combiName(rs.getString("combiName"));
				vo.setStock_contName(rs.getString("contName"));
				vo.setStock_fishName(rs.getString("fishName"));
				vo.setStock_amt(rs.getString("amt"));
				
				list.add(vo);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		close();
		return list;
	}

}
