<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String mode = request.getParameter("mode");
	String link = request.getParameter("before");

if(mode=="DARK"){
	session.setAttribute("sessionMode" , mode);
	response.sendRedirect(link);
}else{
	session.setAttribute("sessionMode", mode);
	response.sendRedirect(link);
}
%>
</body>
</html>