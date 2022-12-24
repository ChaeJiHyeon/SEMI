<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = 'c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script defer type='text/javascript' src='js/fish.js'></script>
<link rel="stylesheet" href="css/inout_list.css">
<title>semi/inout_list.jsp</title>
</head>
<body>
<%
 	String io_code = request.getParameter("io_code");
%>
	<div class="inout_list_main">
			<header class="inout_list">
				<% if(io_code.equals("1")){ %>
					<h2 id="inout_list_h2">입고 조회</h2>
				<% }if(io_code.equals("2")){ %>
					<h2 id="inout_list_h2">출고 조회</h2>
				<%} %>
	
				<form method='post' name='inout_list_page_frm'>
					<div class="inout_list_header_group">
						<input type='hidden' name='nowPage' value="${pageVo.nowPage }"/>
						<input type='hidden' name='io_code' value="<%= io_code %>"/>
						<input id="inout_list_date" name="findDate" type="date" value="${pageVo.date  }">
						<input id="inout_list_text" name="findStr" type="text" value="${pageVo.findStr }">
						<input id="inout_list_button" type="button" value="검색">
					</div>
				</form>
					
			</header>
			<div class="inout_list_table">
			<!-- 테이블 타이틀 영역 -->
				<div class="inout_list_table_header">
					<div>No</div>
					<div>기준일자</div>
					<div>조합명-조합코드</div>
					<div>창고명-창고코드</div>
					<div>어종-코드</div>
					<% if(io_code.equals("1")){ %>
						<div>입고량(t)</div>
					<% }if(io_code.equals("2")){ %>
						<div>출고량(t)</div>
					<%} %>
				</div>
				<c:forEach var='v' items='${list }' varStatus='status'>
					<div class="inout_list_table_items"  onclick="view('${v.io_index}')">
						<div>${status.count+(pageVo.nowPage-1)*pageVo.listSize} </div>
						<div>${v.io_date1}</div>
						<div>${v.io_combiName }-${v.io_combiCode }</div>
						<div>${v.io_contName }-${v.io_contCode }</div>
						<div>${v.io_fishName }-${v.io_fishCode }</div>
						<div>${v.io_amt }</div>
					</div>
				</c:forEach>
			
			</div>
					  
			<div class="inout_list_btn_page">
			<c:if test = "${pageVo.startPage>1 }">
				<input type='button' value='처음' onclick='ioListMovePage(1)'/>
				<input type='button' value='이전' onclick='ioListMovePage(${pageVo.startPage-1})'/>
			</c:if>   
			<c:forEach var='i' begin='${pageVo.startPage }' end='${pageVo.endPage}'>		   
				<input type = 'button' value='${i}' ${pageVo.nowPage eq i?"id='inout_list_now_btn'":'' } onclick='ioListMovePage(${i})'<% Integer a = (Integer)pageContext.getAttribute("i"); %><% if(("move"+a).equals(request.getParameter("page"))){ %>id="selected_move"<%} %>>
			</c:forEach>
			
			<c:if test = "${pageVo.totPage > pageVo.endPage }">
				<input type='button' value='다음' onclick='ioListMovePage(${pageVo.endPage+1})'></input>
				<input type='button'  value='맨끝' onclick='ioListMovePage(${pageVo.totPage})'></input>
			</c:if>
			</div>
		</div>
</body>
</html>