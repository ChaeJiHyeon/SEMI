<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>semi/logout.jsp</title>
</head>
<body>
<%
	//session.setAttribute("sessionId",null);
	session.invalidate();
	response.sendRedirect("login.jsp");
%>
</body>
</html>