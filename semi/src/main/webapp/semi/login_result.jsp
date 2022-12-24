<%@page import="semi.Login"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login_result.jsp</title>
</head>
<body>
<%
String id = request.getParameter("login_id");
String pw = request.getParameter("login_pw");
Login log = new Login();
boolean b = log.login(id, pw);
if(b){
	session.setAttribute("sessionId", id);
	response.sendRedirect("../index.jsp");
}else{
	out.print("<script>alert('아이디, 패스워드를 확인해주세요.');</script>");
	out.print("<script>location.href='login.jsp';</script>");
}
%>
</body>
</html>