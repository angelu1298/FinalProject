<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>	
	<% 
		response.setStatus(HttpServletResponse.SC_OK); 
	%>
	
	<!DOCTYPE html>
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>web study</title>
	<link href="css/style.css" rel="stylesheet" type="text/css"> 
	</head>
	
	<body>
	
		<div class="errorBox">
		
			<h1>500</h1>
			<h3>페이지 내부에 에러가 발생하였습니다</h3>
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