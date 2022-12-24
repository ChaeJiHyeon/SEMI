<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="semi.ListQuery"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semi/inout_input.jsp</title>
<link rel="stylesheet"
	  href="./css/inout_input.css"/>
<script defer src="./js/inout_input.js"></script>
</head>
<body>
<%
	ListQuery lq = new ListQuery();
	List<String> fishList = lq.getDataList("fish");
	List<String> contList = lq.getDataList("cont");
	List<String> combiList = lq.getDataList("combi");
	lq.close();
	
	Date now = new Date();
	Calendar cal = Calendar.getInstance();
	cal.setTime(now);
	cal.add(cal.YEAR, -5);
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String today = formatter.format(cal.getTime());
	
%>
	<div class="input_container">
		<div class="inout_input_header">
			<h2> 입출고 등록 </h2>
		</div>
		<div class="inout_input_main">
			<form name="inout_input_frm" method = "POST" id="frm_inout_input">
				<span>구분</span>
				<label>
					<input type="radio" value="1" name="inout_input_ioCode" checked/>입고
				</label>
				<label>
					<input type="radio" value="2" name="inout_input_ioCode"/>출고
				</label><br/>
				<span>기준일자</span>
				<input type="date" name="inout_input_date" value="<%=today%>"/></br>
				<span>조합명/조합코드</span>
				<input list ="io_combiList" name="inout_input_combiList" id="io_combiList_input"/>
				<datalist id="io_combiList">
					<%for(int i=0; i<combiList.size(); i++){ %>
						<%out.print("<option value='" + combiList.get(i) + "'>"); %>
					<% } %>
				</datalist></br>
				<span>창고명/창고코드</span>
				<input list="io_contList" name="inout_input_contList" id="io_contList_input"/>
				<datalist id="io_contList">
					<%for(int i=0; i<contList.size(); i++){ %>
						<%out.print("<option value='" + contList.get(i) + "'>"); %>
					<% } %>
				</datalist></br>
				<span>어종명/어종코드</span>
				<input list="io_fishList" name="inout_input_fishList" id="io_fishList_input"/>
				<datalist id="io_fishList">
					<%for(int i=0; i<fishList.size(); i++){ %>
						<%out.print("<option value='" + fishList.get(i) + "'>"); %>
					<% } %>
				</datalist></br>
				<span>수량</span>
				<input type="text" name="inout_input_amt" id="io_input_amt"/>
				<div class="inout_input_btn">
					<input type="button" value="입력" name="inout_input_btn_input"/>
					<input type="button" value="취소" name="inout_input_btn_cancle"/>
				</div>
			</form>
		</div>
	</div>
</body>
</html>