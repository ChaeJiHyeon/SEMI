<%@page import="semi.Login"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
<link rel='stylesheet' href='../css/login.css'>

</head>
<body id='login_body'>
<form name='login_frm' id='login_frm' method='post' action='login_result.jsp'>
   <div id='login_div_header'>
   <h1 id='login_h1'>LOGIN</h1>
   </div>
   <div id='login_div'>
      <span>ID</span>
      <input type='text' name='login_id' maxlength='20'
            autocomplete='off' placeholder='아이디를 입력해주세요.' required/><br/>
      <output></output>
      <span>PW</span>
      <input type='password' name='login_pw' maxlength='20' placeholder='패스워드를 입력해주세요.' required/><br/>
      <output></output>
   </div>
   <div id='login_btn'>
      <input type='submit' value='로그인' name='login_btn' id='login_btn'/>
   </div>
   <div id='login_img'></div>
</form>
</body>
</html>