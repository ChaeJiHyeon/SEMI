<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>semi/stock_list.jsp</title>
		<link rel="stylesheet" href="css/stock_list.css">
		<script defer src = "js/fish.js"></script>
		
	</head>
	<body>
		<div class="stock_list_main">
		
			<header class="stock_list_header">
				<h2 id="stock_list_h2">재고 조회</h2>
				<form id ="stock_list_header_group" name = "stock_list_search" method="post">
						<input name="nowPage" type="hidden" value="${pageVo.nowPage }">
						<input id="stock_list_date" type="date" name= "date" value=${pageVo.date }>
						<input name="findStr" type="text" value="${pageVo.findStr }">
						<input name="stock_list_button" type="button" value="검색">
						
				</form>
			</header>
			<div class="stock_list_table">
			<!-- 테이블 타이틀 영역 -->
				<div class="stock_list_table_header">
					<div>No</div>
					<div>기준일자</div>
					<div>조합명 - 조합코드</div>
					<div>창고명 - 창고코드</div>
					<div>어종명 - 표준코드</div>
					<div>수량</div>
				</div>
				<c:forEach var="v" items="${list }" varStatus="status">
					<div class="stock_list_table_items">
						<div>${status.count+(pageVo.nowPage-1)*pageVo.listSize}</div>
						<div>${v.stock_date1 }</div>
						<div>${v.stock_combiName } - ${v.stock_combiCode }</div>
						<div>${v.stock_contName }- ${v.stock_contCode }</div>
						<div>${v.stock_fishName} - ${v.stock_fishCode }</div>
						<div>${v.stock_amt }</div>
					</div>
				</c:forEach>
			
			</div>
			<div class = 'stock_list_btn_page'>
				<c:if test="${pageVo.startPage>1 }">
					<input type = 'button' value='처음' onclick='movePage(1)'>
					<input type = 'button' value='이전' onclick='movePage(${pageVo.startPage-1})'>
				</c:if>
				
				<c:forEach var="i"  begin="${pageVo.startPage }" end="${pageVo.endPage }">
					<input type = 'button' value='${i}' ${pageVo.nowPage eq i?"id='stock_list_now_btn'":'' } onclick='movePage(${i})' <% Integer a = (Integer)pageContext.getAttribute("i"); %><% if(("move"+a).equals(request.getParameter("page"))){ %>id="selected_move"<%} %>>
				</c:forEach>
				<c:if test="${pageVo.totPage > pageVo.endPage }">
					<input type = 'button' value='다음' onclick='movePage(${pageVo.endPage+1})'>
					<input type = 'button' value='끝' onclick='movePage(${pageVo.totPage})'>
				</c:if>
			</div>
		</div>
			
	
	</body>
</html>