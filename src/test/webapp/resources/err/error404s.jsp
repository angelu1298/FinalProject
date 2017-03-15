<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
	<% // 이것은 현재 페이지가 정상적으로 응답되는 페이지임을 지정하는 코드다. 이 코드를 생략하면 웹 브라우저는 자체적인 에러 페이지를 표시한다.
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
		
			<h1>404</h1>
			<h3>요청하신 페이지를 찾을 수 없습니다.</h3> 
			<input type="button" value="이전페이지" onclick="history.back()" />
			<input type="button" value="메인페이지" onclick="location.href('/index.jsp');" />
		</div>
		
	</body>
</html>