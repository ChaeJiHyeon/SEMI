package semi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.DBConn;

public class ListQuery {
   Connection conn;
   PreparedStatement ps;
   ResultSet rs;
   
   public ListQuery() {
      conn = new DBConn().getConn();
   }

   public List<String> getDataNameList(String type) {
      String sql = "select " + type + "Name from inout_table group by "+type+"Name";
      List<String> dataList = new ArrayList<String>();
      try {
         ps = conn.prepareStatement(sql);
         rs = ps.executeQuery();
         while(rs.next()) {
            dataList.add(rs.getString(type+"Name"));
         }
         
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return dataList;
   }   
   
   public List<String> getDataStockList(String type) {
      String sql = "select " + type + "Name," + type + "Code from stock group by "+type+"Name,"+type+"Code";
      List<String> dataList = new ArrayList<String>();
      try {
         ps = conn.prepareStatement(sql);
         rs = ps.executeQuery();
         while(rs.next()) {
            dataList.add(rs.getString(type+"Name") + "-" + rs.getString(type+"Code"));
         }
         
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return dataList;
   }   

   public List<String> getDataList(String type) {
      String sql = "select " + type + "Name," + type + "Code from inout_table group by "+type+"Name,"+type+"Code";
      List<String> dataList = new ArrayList<String>();
      try {
         ps = conn.prepareStatement(sql);
         rs = ps.executeQuery();
         while(rs.next()) {
            dataList.add(rs.getString(type+"Name") + "-" + rs.getString(type+"Code"));
         }
         
         
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return dataList;
   }
   
   public void close() {
      try {
         rs.close();
         ps.close();
         conn.close();
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   
}