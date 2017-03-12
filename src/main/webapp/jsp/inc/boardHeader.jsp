<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-cache"/> 
<meta http-equiv="Expires" content="0"/> 
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Burn Fat</title>
<link href="${pageContext.request.contextPath}/resources/css/sub.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script> 
<script src="${pageContext.request.contextPath}/resources/js/gboard.js"></script> 

</head>

<body>

	<%@ page session='true' %>
	<%
		request.setCharacterEncoding("UTF-8");

		String usernum = "1";
		session.setAttribute("usernum", usernum); 
		String username = "soyeon";
		session.setAttribute("username", username); 
		String userpass = "1111";
		session.setAttribute("userpass", userpass); 
		String mem_id = "mimi";
		session.setAttribute("mem_id", mem_id); 
	%>

	<!-- S: wrap -->
	<div class="wrap">
	
		<div class="modal_bg off">
		
		</div>
		
		<header>
			<!-- gnb 영역 -->
			<%@ include file="../inc/gnb.jsp"%>
			<!-- lnb 영역 -->
			<%@ include file="../inc/lnb.jsp"%>
		</header>