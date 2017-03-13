<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
		response.setHeader("Pragma","No-cache"); //HTTP 1.0 
		response.setDateHeader ("Expires", 0); 
		response.setHeader ("Cache-Control", "no-cache"); 
	%>

<nav class="gnb">
	<%--로그인 여부 확인 세션에 아이디값으로...--%>
	<ul>
		<c:if test="${!empty sessionScope.mem_id }">
		
				<li><a href="./mem_modify.brn">${sessionScope.mem_id } 님 환영합니다! </a></li>
				<li><a href="./Logout.brn">LOGOUT</a></li>
		 		<li><a href="./Mypage.brn">MYPAGE</a></li>
		 		
			 <c:if test="${sessionScope.mem_id=='admin'}"> 
				<li><a href="./memlist.brn">MEMBER </a></li>
			</c:if> 
		 </c:if>
		<c:if test="${empty sessionScope.mem_id }">
		 	<li><a href="./Login.brn">LOGIN</a></li>
		</c:if>
	</ul> 
	
</nav>
