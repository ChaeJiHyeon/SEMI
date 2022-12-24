<%@page import="java.util.List"%>
<%@page import="semi.Page"%>
<%@page import="semi.FishVo"%>
<%@page import="semi.ListQuery"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semi/inout_modify.jsp</title>
<link rel="stylesheet"
	  href="./css/inout_modify.css"/>
<script defer src="./js/inout_modify.js"></script>
</head>
<body>
	
<%

	
	
	FishVo fVo = (FishVo)request.getAttribute("fVo");
	Page pageVo = (Page)request.getAttribute("pageVo");
	
	
	String amt = fVo.getIo_amt();
	//수정하기 이전의 수량
	String preAmt = amt;
		
	String date = fVo.getIo_date1();
	String combi = fVo.getIo_combiName() + "-" + fVo.getIo_combiCode();
	String cont = fVo.getIo_contName() + "-" + fVo.getIo_contCode();
	String fish = fVo.getIo_fishName() + "-" + fVo.getIo_fishCode();
	String index = fVo.getIo_index();
	String ioCode = fVo.getIo_code();
	
	int nowPage = pageVo.getNowPage();
	String findStr = pageVo.getFindStr();
	

	ListQuery lq = new ListQuery();
	List<String> fishList = lq.getDataList("fish");
	List<String> contList = lq.getDataList("cont");
	List<String> combiList = lq.getDataList("combi");
	lq.close();
	
	

%>
	<div class="modify_container">
		<div class="inout_modify_header">
			<h2><%=ioCode.equals("1") ? "입고" : "출고" %> 수정 </h2>
		</div>
		<div class="inout_modify_main">
			<form name="inout_modify_frm" method ="POST" id="frm_inout_modify">
				<span>기준일자</span>
				<input type="date" name="inout_modify_date" value="<%=date%>"/></br>
				<span>조합명/조합코드</span>
				<input list ="io_combiList" name="inout_modify_combiList" value="<%=combi%>" 
					   id="io_combiList_modify"/>
				<datalist id="io_combiList">
					<%for(int i=0; i<combiList.size(); i++){ %>
						<%out.print("<option value='" + combiList.get(i) + "'>"); %>
					<% } %>
				</datalist></br>
				<span>창고명/창고코드</span>
				<input list="io_contList" name="inout_modify_contList" value="<%=cont%>"
					   id="io_contList_modify"/>
				<datalist id="io_contList">
					<%for(int i=0; i<contList.size(); i++){ %>
						<%out.print("<option value='" + contList.get(i) + "'>"); %>
					<% } %>
				</datalist></br>
				<span>어종명/어종코드</span>
				<input list="fishList_modify" name="inout_modify_fishList" value="<%=fish %>" />
				<datalist id="fishList_modify">
					<%for(int i=0; i<fishList.size(); i++){ %>
						<%out.print("<option value='" + fishList.get(i) + "'>"); %>
					<% } %>
				</datalist></br>
				<span>수량</span>
				<input type='text' name="inout_modify_amt" value ='<%=amt%>'/>
		
				<!-- 수정하기 이전의 수량 -->
				<input type="hidden"  name="inout_modify_preAmt" value="<%=preAmt%>" />
				<input type="hidden" name="inout_modify_index" value="<%=index%>"/>
				<input type='hidden' name="io_code" value="<%=ioCode%>"/>
				<!--  Page -->
				<input type="hidden" name="nowPage" value="<%=nowPage%>"/>
				<input type="hidden" name="findStr" value="<%=findStr%>"/>
				
				<div class="inout_modify_btn">
					<input type="button" value="수정" name="inout_modify_btn_modify"/>
					<input type="button" value="삭제" name="inout_modify_btn_delete"/>
					<input type="button" value="취소" name="inout_modify_btn_cancle"/>
				</div>
			</form>
		</div>
	</div>
</body>
</html>