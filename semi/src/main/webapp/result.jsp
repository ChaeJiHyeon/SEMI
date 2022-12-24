<%@page import="semi.FishVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="pageVo" class="semi.Page"/>
<jsp:setProperty property="*" name="pageVo"/>

<jsp:useBean id="fVo" class="semi.FishVo"/>
<jsp:setProperty property="*" name="fVo"/>


<%
	request.setAttribute("pageVo", pageVo);
	request.setAttribute("fVo", fVo);
%>

<jsp:forward page="fish.do"/>
