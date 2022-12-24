<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%if("DARK".equals(session.getAttribute("sessionMode"))){	%>
<link id="css_id" rel='stylesheet' href='./css/index2.css'>
<%System.out.println("dark");}else {%>
<link id="css_id" rel='stylesheet' href='./css/index.css'>
<%} %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>semi_project.jsp</title>
<script defer src ="js/fish.js"></script>
<script type="text/javascript">

//  window.history.forward();
//  function noBack(){window.history.forward();}
</script>
</head>
<!-- <body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload=""> -->
<body>
	<main class="index_main">
	<%
		String inc = "temp.html";
		if(request.getParameter("inc")!=null){
			inc = request.getParameter("inc");
		}
		
		String sessionId = null;
		if(session.getAttribute("sessionId") != null){
			sessionId = (String)session.getAttribute("sessionId");
		
		}
	%>
	<%if(sessionId != null){ %>
		<header class="index_header">
			<div class = "header_title" onclick="location.href='index.jsp'" >
				<img src="img/하트고기2.png" id="logo"/>
				<div>
				<h1 id="index_h1">입출고 관리 시스템</h1>
				
				<h3 id="index_h3"><관리자 페이지></h3>
				</div>
			</div>
			<div class = "header_div">
				<%if("DARK".equals(session.getAttribute("sessionMode"))){	%>
				<input type="button" id= "toggle" onclick="toggleFunc()" value="WHITE"></input>
				<% }else {%>
				<input type="button" id= "toggle" onclick="toggleFunc()" value="DARK"></input>
				<%} %>
				<div id="welcome"><%=sessionId %>님 어서오세요! </div>
				<div id="index_btn_div"><input id="index_btn" type= "button" value="로그아웃" onclick="location.href='semi/logout.jsp'"></div>
			</div>
			
		</header>
		
		<div class = "main">
			<nav class="index_nav"> 
				<div class="sticky_nav">
	               <div class="out_stats" <%if(("semi/inout_search2.jsp".equals(request.getParameter("inc"))||("semi/inout_stats2.jsp".equals(request.getParameter("inc")))) && "2".equals(request.getParameter("io_job"))) {%>id ="selected_nav1"<%} %>onclick="location.href='index.jsp?inc=semi/inout_search2.jsp&io_code=2&io_job=2'" >
	                  <h2 class="index_h2">출고현황</h2>
	               </div>
	               <div class="out_list" <%if("semi/inout_list.jsp".equals(request.getParameter("inc"))&& "2".equals(request.getParameter("io_code"))) {%>id ="selected_nav2"<%} %>onclick="location.href='fish.do?job=io_select&io_code=2'">
	                  <h2 class="index_h2">출고조회</h2>
	               </div>
	               <div class="in_stats" <%if(("semi/inout_search2.jsp".equals(request.getParameter("inc"))||("semi/inout_stats2.jsp".equals(request.getParameter("inc")))) && "1".equals(request.getParameter("io_job"))) {%>id ="selected_nav3"<%} %>onclick="location.href='index.jsp?inc=semi/inout_search2.jsp&io_code=1&io_job=1'">
	                  <h2 class="index_h2">입고현황</h2>
	               </div>
	               <div class="in_list" <%if("semi/inout_list.jsp".equals(request.getParameter("inc"))&& "1".equals(request.getParameter("io_code"))) {%>id ="selected_nav4"<%} %>onclick="location.href='fish.do?job=io_select&io_code=1'">
	                  <h2 class="index_h2">입고조회</h2>
	               </div>
					<div class="inout_input" <%if("semi/inout_input.jsp".equals(request.getParameter("inc"))) {%>id ="selected_nav5"<%} %>onclick="location.href='index.jsp?inc=semi/inout_input.jsp'">
						<h2 class="index_h2">입출고등록</h2>
					</div>
					<div class="stock_list" <%if("stock_select".equals(request.getParameter("job"))) {%>id ="selected_nav6"<%} %>onclick="location.href='stock_list.do?job=stock_select'">
						<h2 class="index_h2">재고조회</h2>
					</div>
					<div class="stock_change" <%if("change_main".equals(request.getParameter("job"))||"change_select".equals(request.getParameter("job"))) {%>id ="selected_nav7"<%} %>onclick="location.href='stock.do?job=change_main'">
						<h2 class="index_h2">재고변동표</h2>
					</div>
				</div>
			</nav>
			<div class = "sub_page">
           		  <jsp:include page='<%=inc %>'/>
          		  <div class="top" style="cursor:pointer;" onclick="window.scrollTo(0,0);">TOP</div>
            </div>
		</div>
		<!-- div:main -->
		<footer>
			<div class="index_footer">	
				<h3>SEMI GOGI SAJO PROJECT</h3>
				<h4>Address : semi gogi sajuseyo</h4>
				<h4>Tel : 010-9292-4545</h4>
				<a href="https://m.facebook.com/gogoop4/photos/a.112618573783700/159544109091146/?type=3" class="fa fa-facebook"></a>
				<a href="https://twitter.com/twt_recipes/status/1098914729269948416" class="fa fa-twitter"></a>
				<a href="https://www.google.com/search?q=%EC%84%9C%EC%9A%B8%EB%8C%80%EC%9E%85%EA%B5%AC+%EA%B3%A0%EA%B9%83%EC%A7%91&tbm=isch&ved=2ahUKEwij-MHWybT7AhX9RvUHHc96BxcQ2-cCegQIABAA&oq=%EC%84%9C%EC%9A%B8%EB%8C%80%EC%9E%85%EA%B5%AC+%EA%B3%A0%EA%B9%83%EC%A7%91&gs_lcp=CgNpbWcQAzIHCAAQgAQQGDoECCMQJzoHCCMQ6gIQJzoICAAQgAQQsQM6BQgAEIAEOggIABCxAxCDAToGCAAQBRAeUOwDWO8dYLsgaANwAHgDgAH4AYgB4y-SAQYwLjMxLjSYAQCgAQGqAQtnd3Mtd2l6LWltZ7ABCsABAQ&sclient=img&ei=_9J1Y-P8E_2N1e8Pz_WduAE&rlz=1C5CHFA_enKR1016KR1016" class="fa fa-google"></a>
				<a href="https://www.instagram.com/explore/tags/%EA%B3%A0%EA%B9%83%EC%A7%91/" class="fa fa-instagram"></a>
				<a href="https://www.youtube.com/watch?v=gqrBngRMEPg" class="fa fa-youtube"></a>
				<hr id="footer-line" style="width:90%; opacity:0.5;"/>
		     	<h6 class="m-0 mt-4 text-center text-white text-capitalize">Copyright &copy; 2022 designed by gogisajo</h6>
			</div>
		</footer>
	<% } else{
		response.sendRedirect("semi/login.jsp");
	}
	%>
	</main>
	
</body>
</html>