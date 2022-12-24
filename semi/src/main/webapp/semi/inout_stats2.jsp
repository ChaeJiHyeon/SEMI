<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = 'c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>semi/inout_stats.jsp</title>
		<script defer type='text/javascript' src='js/fish.js'></script>
		<script type="text/javascript">
			window.onpagehow = function(event){
				if(event.persisted || (window.performance && window.performance.navigation.type == 2)){
					loacation.href='index.jsp?inc=semi/inout_search2.jsp&io_code=2&io_job=2';
				}
			}
		</script>
		<link rel="stylesheet" href="css/inout_stats2.css">
	</head>
	<body>
		<div class="inout_stats_main">
			<header class="inout_stats">
				<h2 id="inout_stats_h2">
				${fVo.io_code eq 1?'입고 현황 ':'출고 현황 ' }
				</h2>
				<h5 class="inout_stats_h5"> / ${fVo.io_date1 } ~ ${fVo.io_date2 }</h5>
				<c:if test="${fVo.io_combiName != null && fVo.io_combiName ne ''}">
					<h5 class="inout_stats_h5"> / ${fVo.io_combiName } </h5>
				</c:if>
				<c:if test="${fVo.io_contName != null && fVo.io_contName ne ''}">
					<h5 class="inout_stats_h5"> / ${fVo.io_contName } </h5>
				</c:if>
				<c:if test="${fVo.io_fishName != null && fVo.io_fishName ne ''}">
					<h5 class="inout_stats_h5"> / ${fVo.io_fishName } </h5>
				</c:if>
			</header>
			<div class="inout_stats_table">
			<!-- 테이블 타이틀 영역 -->
				<div class="inout_stats_header">
					<div>No</div>
					<div>${fVo.io_type }</div>
					<div>${fVo.io_code eq 1?'입고량(t)':'출고량(t)' }</div>
				</div>
				<c:forEach var='v' items='${list }' varStatus='status'>
				<div class="inout_stats_items">
					<div>${status.count+(pageVo.nowPage-1)*pageVo.listSize} </div>
					<div>${ v.io_type}</div>
					<div>${ v.io_amt}</div>
				</div>
				</c:forEach>
			
			</div>
			
			<form method='post' name='inout_stats_page_frm'>
				<input type='hidden' name='io_combiName' value="${fVo.io_combiName }"/>
				<input type='hidden' name='io_contName' value="${fVo.io_contName }"/>
				<input type='hidden' name='io_fishName' value="${fVo.io_fishName }"/>			
				<input type='hidden' name='io_type' value="${fVo.io_type }"/>
				<input type='hidden' name='io_date1' value="${fVo.io_date1 }"/>
				<input type='hidden' name='io_date2' value="${fVo.io_date2 }"/>
				<input type='hidden' name='io_code' value="${fVo.io_code }"/>
				<input type='hidden' name='nowPage' value="${pageVo.nowPage }"/>
			</form>		  
			<div class="inout_stats_btn_page">
			<c:if test = "${pageVo.startPage>1 }">
				<input type='button' value='처음' onclick='IoStatsMovePage(1)'/>
				<input type='button' value='이전' onclick='IoStatsMovePage(${pageVo.startPage-1})'/>
			</c:if>   
			<c:forEach var='i' begin='${pageVo.startPage }' end='${pageVo.endPage}'>	
				<input type='button' value='${i}' ${pageVo.nowPage eq i?"id='inout_stats_now_btn'":'' } onclick='IoStatsMovePage(${i})'<% Integer a = (Integer)pageContext.getAttribute("i"); %><% if(("move"+a).equals(request.getParameter("page"))){ %>id="selected_move"<%} %>>	   
			</c:forEach>
			
			<c:if test = "${pageVo.totPage > pageVo.endPage }">
				<input type='button' value='다음' onclick='IoStatsMovePage(${pageVo.endPage+1})'></input>
				<input type='button' value='맨끝' onclick='IoStatsMovePage(${pageVo.totPage})'></input>
			</c:if>
			</div>
		</div>
			
	
	</body>
</html>