cccc<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
		response.setHeader("Pragma","No-cache"); //HTTP 1.0 
		response.setDateHeader ("Expires", 0); 
		response.setHeader ("Cache-Control", "no-cache"); 
	%>

<%--
		String result = "";	
		String outlogTime ="";
		try {
			result = "OFF";
			outlogTime = (String) session.getAttribute("s_loginTime"); 
			// 오브젝트 형으로 저장되기 때문에 캐스팅해주어야한당~
			
			if(!outlogTime.equals("")){
				result = " ON (로그인시간: " + outlogTime  + "로그인유지시간:" +  session.getMaxInactiveInterval() + ")";
			} else {
				result = " OFF ";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
--%> 

<nav class="gnb">
	<%--로그인 여부 확인 세션에 아이디값으로...--%>
	<ul>
		<c:if test="${!empty sessionScope.mem_id }">
			<li><a href="./Logout.brn">LOGOUT</a></li>
		 	<li><a href="./Mypage.brn">MYPAGE</a></li>
		 </c:if>
		<c:if test="${empty sessionScope.mem_id }">
		 	<li><a href="./Login.brn">LOGIN</a></li>
		</c:if>
	</ul> 
	
</nav>
