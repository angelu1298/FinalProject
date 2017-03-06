<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>	
	<% // 이것은 현재 페이지가 정상적으로 응답되는 페이지임을 지정하는 코드다. 이 코드를 생략하면 웹 브라우저는 자체적인 에러 페이지를 표시한다.
		response.setStatus(HttpServletResponse.SC_OK); 
	%>
	<!DOCTYPE html>
	<html>
	<head>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>web study</title>
	<link href="css/style.css" rel="stylesheet" type="text/css"> 
	<link href="css/style.css" rel="stylesheet" type="text/css"> 
		<script>
		$(function(){
			setTimeout(function(){
				history.go(-1);
			},10000);	
		})
		</script>
	</head>
	
	<body>
		<div class="errorBox">
		
			<h1>405</h1>
			<h3>
				잘못된 URL로 접근하셨습니다.<br/>
				10초후 이전 페이지로 이동합니다.
			</h3>
			<p><%=exception.getClass().getName()%></p>
			<h3>error Message</h3>
			<p><%=exception.getMessage()%></p> 
			<div class="mt30">
			<input type="button" value="이전페이지로" onclick="history.back()" />
			<input type="button" value="메인페이지로" onclick="location.href('/index.jsp');" />
			</div>
		</div>
		
	</body>
</html>