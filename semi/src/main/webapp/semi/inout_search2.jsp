<%@page import="semi.ListQuery"%>
<%@page import="java.util.List"%>
<%@page import="semi.FishVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = 'c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script defer type='text/javascript' src='./js/fish.js'></script>
<link href='css/inout_search.css' rel='stylesheet'>
<script defer src="./js/io_select.js"></script>

<title>semi/inout_search</title>
</head>
<body>
<%
   String io_code = request.getParameter("io_code");
   ListQuery lq = new ListQuery();
   List<String> fishNameList = lq.getDataNameList("fish");
   List<String> contNameList = lq.getDataList("cont");
   List<String> combiNameList = lq.getDataList("combi");
%>
   <div id="inout_search">
   <div id="inout_search_header">
      <% if(io_code.equals("1")){ %>
         <h2>입고현황</h2>
      <% }if(io_code.equals("2")){ %>
         <h2>출고현황</h2>
      <%} %>
   </div>
   
   <form method='post' id="inout_search_frm" name="inout_search_frm">
      <div id="inout_search_frm_type">
         <span>구분</span>
         <div id="inout_search_frm_type_detail">
            <label>월별<input type="radio" name="io_type" value="월별" onclick="input_check_disable(this)"/></label>
            <label>일자별<input type="radio" name="io_type" value="일자별" onclick="input_check_disable(this)"/></label>
            <label>품목별<input type="radio" name="io_type" value="품목별" onclick="input_check_disable(this)" checked/></label>
         </div>
      </div>
      <div id="inout_search_frm_search">
         <div id="inout_search_date_search_wrapper">
            <span class="inout_search_frm_search_span">기준일자</span>
            <input type="date" name="io_date1" value="2017-03-01">
            <input type="date" name="io_date2" value="2017-07-01">
         </div>
         <div>
            <span class="inout_search_frm_search_span">조합명</span>
            <input list="io_combiName_list"  name="io_combiName" id="io_combiName_list_input"/>
               <datalist id="io_combiName_list">
                  <%for(int i=0; i<combiNameList.size(); i++){ %>
                     <%out.print("<option value='" + combiNameList.get(i) + "'>"); %>
                  <% } %>
               </datalist>
         </div>
         <div>
            <span class="inout_search_frm_search_span">창고명</span>
            <input list="io_contName_list" name="io_contName" id="io_contName_list_input"/>
            <datalist id="io_contName_list">
               <%for(int i=0; i<contNameList.size(); i++){ %>
                  <%out.print("<option value='" + contNameList.get(i) + "'>"); %>
               <% } %>
            </datalist>
         </div>
         <div>
            <span class="inout_search_frm_search_span" name="io_fishName">표준어종명</span>
            <input list="io_fishName_list" name="io_fishName" id="io_fishName_list_input"/>
            <datalist id="io_fishName_list">
               <%for(int i=0; i<fishNameList.size(); i++){ %>
                  <%out.print("<option value='" + fishNameList.get(i) + "'>"); %>
               <% } %>
            </datalist>
         </div>
      </div>
      <input type="hidden" name="nowPage" value="1">
      <input type="hidden" name="io_code" value=<%= io_code %>> 
      <div id="inout_search_btn_search_wrapper">
         <input type='button' value='검 색' id="inout_search_btn_search" name="inout_search_btn_search">
      </div>
   </form>
   </div>
</body>
</html>