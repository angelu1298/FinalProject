<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Calendar"%>
<!DOCTYPE html>
<html ng-app>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-cache"/> 
<meta http-equiv="Expires" content="0"/> 
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Burn Fat</title>
<link href="${pageContext.request.contextPath}/resources/css/sub.css" rel="stylesheet" type="text/css">
<script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/angular.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/common.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/sboard.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/fboard.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/list.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/gboard.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>

</head>

<body>

	<%@ page session='true' %>
	<%--
		request.setCharacterEncoding("UTF-8");
		// 가입시간
		String logintime = "";
		logintime = request.getParameter("loginTime");
		session.setAttribute("s_loginTime", logintime);
	--%>

	<!-- S: wrap -->
	<div class="wrap">

		<header>
			<!-- gnb 영역 -->
			<%@ include file="/jsp/inc/gnb.jsp"%>
			<!-- lnb 영역 -->
			<%@ include file="/jsp/inc/lnb.jsp"%>
		</header>
		
		<div class="modal_bg off">
		
		</div>